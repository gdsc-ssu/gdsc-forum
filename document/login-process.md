# 로그인 프로세스

## SecurityConfiguration

- csrf 보안을 사용하지 않는다.

```java
http.csrf(AbstractHttpConfigurer::disable);
```

- HTTP 응답 헤더에 X-Frame-Options: SAMEORIGIN 헤더를 추가하여 동일한 출처에서만 페이지가 프레임 내에 렌더링되도록 제한한다.

```java
http
        .headers(headers->
        headers.addHeaderWriter(new XFrameOptionsHeaderWriter(
        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)
        )
        );
```

- jwt의 401, 403 exception 핸들러를 추가한다.

```java
http.exceptionHandling(
        exceptionHandling->exceptionHandling
        .authenticationEntryPoint(authenticationEntryPoint())
        .accessDeniedHandler(accessDeniedHandler())
        );
```

- 세션을 사용하지 않기 때문에 STATELESS 상태로 설정한다.

```java
http.sessionManagement(
        sessionManagement->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
```

- 요청에 대한 사용 권한을 체크한다. 명시적으로 나타낸 uri 외 나머지 요청은 인증이 필요하다.

```java
http.authorizeHttpRequests(
        authorize->authorize
        .requestMatchers(request->request.getRequestURI().startsWith("/swagger-ui")).permitAll()
        .requestMatchers(request->request.getRequestURI().startsWith("/v3/api-docs")).permitAll()
        .requestMatchers(request->request.getRequestURI().startsWith("/auth/sign-up")).permitAll()
        .requestMatchers(request->request.getRequestURI().startsWith("/auth/sign-in")).permitAll()
        .requestMatchers(request->request.getRequestURI().startsWith("/dev/ping")).permitAll()
        .requestMatchers(request->request.getRequestURI().startsWith("/h2-console")).permitAll()
        .anyRequest().authenticated()
        );
```

- JwtFilter를 UsernamePasswordAuthenticationFilter 앞에 동작하게 한다. Spring Security에서 제공하는
  UsernamePasswordAuthenticationFilter는 아이디, 비밀번호로 사용자를 검증하는데 우리는 jwt를 쓸 것이게 앞에 설정해준다.

```java
http.addFilterBefore(
        jwtFilter(),
        UsernamePasswordAuthenticationFilter.class
);
```

## JwtService

- 토큰의 유효 기간(application.yaml)과 토큰을 서명하는 데 사용할 알고리즘(JwtAlgorithmConfiguration)을 주입 받아 사용하고, 사용자 id와 역할을 클레임으로 추가하여 jwt
  토큰을 생성한다.

> JWT는 `.` 을 구분자로 3가지의 문자열로 되어 있다.
>
> ![jwt.png](/image/jwt.png)
> - Payload 부분에는 토큰에 담을 정보가 들어있다. 여기에 담는 정보의 한 ‘조각’을 클레임이라 부르고, 이는 name / value의 한 쌍으로 이루어져 있다.

- SecurityContextHolder를 통해 현재 인증된 사용자의 정보를 가져온다.

## JwtVerifier

- HTTP 요청에서 Authorization 헤더를 추출하여 토큰을 검증한다.
- “Bearer test” 토큰이 로컬 프로파일에서 사용될 경우, id가 1인 사용자의 정보를 반환한다.
- 토큰이 “Bearer test”가 아니면, Bearer 접두사를 제거한 후 토큰에 userId와 userRole 클레임이 존재하는지 확인하여 검증한다.
- 검증 중 토큰이 만료되면 TokenExpiredException을 던지고, 다른 검증 오류가 발생하면 JWTVerificationException을 던진다.

## JwtAuthenticationEntryPoint

- 유효한 자격 증명을 제공하지 않고 접근하려 할 때 401 에러를 리턴한다.

## JwtAccessDeniedHandler

- 필요한 권한이 존재하지 않을 경우 403 Forbidden 에러를 리턴한다.

## 1. 로그인

1. 로그인을 할 때는 jwt로 검증을 할 수 없다. 로그인을 해야 jwt 토큰이 발급되기 때문이다. 그렇기 때문에 로그인을 진행하는 endpoint는 JwtFilter에서 검증을 진행하지 않도록 설정하고 수동으로
   인증해준다.

```java
http.authorizeHttpRequests(
        authorize->authorize
        .requestMatchers(request->request.getRequestURI().startsWith("/auth/sign-in")).permitAll()
        .anyRequest().authenticated()
        );
```

1. 사용자로부터 입력 받은 로그인 정보가 db에 있는 유저의 이메일, 비밀번호와 일치한지 확인한다.

```java
User user=rawUserService.getOptionalUserByEmail(email)
        .orElseThrow(()->new IllegalArgumentException("가입되지 않은 이메일입니다."));

        if(!passwordEncoder.matches(password,user.getPassword())){
        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
```

1. 일치하다면, 사용자의 정보(id, role)를 바탕으로 access token과 refresh token을 생성하여 클라이언트에게 전달한다. refresh token은 따로 DB에 저장한다.

```java
AuthTokenDto accessToken=jwtService.createAccessToken(user.getId(),user.getRole());
        AuthTokenDto refreshToken=jwtService.createRefreshToken(user.getId(),user.getRole());

        user.setRefreshToken(refreshToken.getToken());

        return SignResponse.of(accessToken,refreshToken);
```

## 2. 로그인 이후의 요청(JWT 유효성 검사)

1. 로그인을 할 때는 사용자의 이메일과 비밀번호를 수동으로 검증하고 로그인 이후의 요청은 클라이언트가 헤더에 jwt를 갖고 요청을 하기에 JwtFilter에서 jwt만 검증해주면 된다.

1. JwtVerifier를 사용하여 요청의 jwt 토큰을 검증한다. TokenDto를 기반으로 UsernamePasswordAuthenticationToken 객체를 생성하고 이를 SecurityContext에
   저장한다. 마지막으로, 요청과 응답을 다음 필터로 전달한다.

```java
@Override
protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain)throws ServletException,IOException{
        TokenDto verifiedTokenDto=jwtVerifier.verify(request);

        authenticate(verifiedTokenDto);

        filterChain.doFilter(request,response);
        }

private UsernamePasswordAuthenticationToken authenticate(TokenDto tokenDto){
        UsernamePasswordAuthenticationToken authenticationToken=null;
        if(tokenDto!=null){
        authenticationToken=new UsernamePasswordAuthenticationToken(
        tokenDto,
        tokenDto.getUserId(),
        Collections.singletonList(new SimpleGrantedAuthority(tokenDto.getUserRole())));
        }

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return authenticationToken;
        }
```

<aside>
💡 SecurityConfig에서 hasRole로 접근 제한을 설정했을 때 주의해야 할 점이 있다. hasRole을 불러올 때 prefix가 ROLE_ 접두사를 자동적으로 붙여준다. 따라서, 유저의 role을 설정하는 부분  `Collections.singletonList(new SimpleGrantedAuthority(tokenDto.getUserRole()))` 에서 SimpleGrantedAuthority 객체 생성 시 role을 ROLE_XXX 형태로 넣어주어야 한다.

</aside>