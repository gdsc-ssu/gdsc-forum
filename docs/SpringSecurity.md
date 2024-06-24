## JWT

Json Web Token?

- 정보를 JSON 객체로 안전하게 전송하기 위한 개방형 표준이다.
- HMAC 알고리즘 또는 RSA 또는 ECDSA를 사용하는 공개/개인 키 쌍을 사용하여 서명할 수 있다.
- JWT를 암호화하여 당사자간에 비밀을 제공할 수 있지만 서명 된 토큰에 중점을 둔다.
    - 서명된 토큰 안에 클레임(내가 쓴 정보)의 무결성을 확인할 수 있다.

#### JWT 구조

`xxxxx.yyyyy.zzzzz`

- x 부분 = header
    - 사용중인 서명 알고리즘 (HMAC SHA256)
    - 토큰 유형 (JWT)
    - **base64Url**로 인코딩 되어있다. (암호화와 복호화를 한다)

```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```

- y 부분 = payload
    - 실질적인 정보 부분이다.
    - **Registered claims**: 발행자(sub)가 누구인지 만료시간(exp), 주제(sub), 청중(aud) (메타데이터 느낌으로 넣지 않아도 된다. 하지만 넣는 것이 권장됨)
    - **Public claims**: JWT 사용자 원하는 대로 정의 가능 (충돌을 피하기 위해서 IANA JSON 웹 토큰 레지스트리에서 정의하거나 충돌 방지 namespace를 포함하는 url로
      정의해야한다.)
    - **Private claims**: 전달할 정보(userID같은 것들이 들어간다 아래 admin부분에)

```json
{
  "sub": "123456789"
  "name": "John Doe"
  "admin": true
}
```

- z 부분 = signature
    - header, payload, secret(나만 알고있는 개인키)를 HMAC, SHA256으로 암호화 (header에서 명시된 alg, base64가 SHA256이다)

```json
HMACSHA256(
base64UrlEncode(header) + "." +
base64UrlEncode(payload),
secret)
```

## Token Generation

- JwtService 클래스에서 JWT token을 생성한다.
- Token은 TokenDto에 명시되어 있는 것과 같이 user Id와 role을 가지고 있다. 그리고 이것들은 HMAC256 알고리즘으로 암호화 되어있다.

```java
private AuthTokenDto createToken(Integer id,String role,Long tokenValidMilliseconds){
        Date expiredAt=new Date(System.currentTimeMillis()+tokenValidMilliseconds);

        String token=JWT.create()
        .withClaim("userId",id)
        .withClaim("userRole",role)
        .withExpiresAt(expiredAt)
        .sign(tokenAlgorithm);

        return new AuthTokenDto(token,tokenValidMilliseconds);
        }
```

이 부분에서 Token을 만들고 Toekn 유효시간이 access, refresh가 서로 다르기 때문에 각각의 메소드를 따로 만들어서 유효시간을 넣어준다.

검증은 rawUserService가 한다.

## Token Verification

- JwtVerifier 클래스에서 들어오는 request에 토큰 검증을 하게 된다.
- auth0 클래스에 JWTVerifier 클래스가 있는데

  Token has not only a proper JWT format, but also its signature matches.

  이런 역할을 한다.

- 인증을 완료하고 TokenDto를 반환하면 된다.

## Security Configuration

- SecurityConfiguration 클래스는 HTTP security를 설정한다.
    - CSRF 비활성화
    - 인증 없이 요청을 받을 endpoint 설정
    - JWT 기반 인증을 다루기 위한 filter를 추가한다.
- 주요 Bean
    - BCryptPasswordEncoder : 비밀번호 암호화를 위한 빈이다.
    - **JwtAuthenticationEntryPoint**와 **JwtAccessDeniedHandler**: 인증 및 접근 거부 시 처리할 핸들러를 정의한다.
    - **JwtFilter**: JWT 토큰을 검증하는 필터이다. (생성자로 JwtVerifier를 받는다.
- SecurityFilterChain
    - CSRF를 비활성화 한다.
    - CORS 설정
    - 예외 처리 :  인증되지 않은 접근과 접근 거부 시 핸들러를 설정한다.
    - 세션 관리 : 세션을 사용하지 않고 stateless로 설정한다.
        - 세션 ID를 서버가 기억하고 있는 것은 확장성 측면에서 좋지 않기 때문에 stateless로 설정을 한다.
    - JWT필터를 UsernamePasswordAuthenticationFilter 앞에 추가한다.
    - 요청 허용
        - permitAll() : 걍 통과 시켜 줘라 (인증을 안해도 됨)
        - authenticated() : 이 경로는 인증을 해라!

![](https://velog.velcdn.com/images/peachkyu/post/3dbc9959-2fbb-46f8-a929-38505b762c08/image.png)
Spring Security는 DelegatingFilterProxy라는 필터를 만들어 메인 Filter Chain에 끼워넣는다. 때문에 DelegatingFilterProxy가 Bean으로 등록된 Spring
Security의 필터를 사용하는 시작점이 된다.
`http.addFilterBefore(
jwtFilter(),
UsernamePasswordAuthenticationFilter.class
);`를 사용해서 순서를 지정해준다. 


