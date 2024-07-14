package com.example.gdscforum.common.security.configuration;

import com.auth0.jwt.algorithms.Algorithm;
import com.example.gdscforum.common.security.filter.JwtAccessDeniedHandler;
import com.example.gdscforum.common.security.filter.JwtAuthenticationEntryPoint;
import com.example.gdscforum.common.security.filter.JwtFilter;
import com.example.gdscforum.common.security.jwt.JwtVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtVerifier jwtVerifier;

    //비밀번호를 안전하게 해시하는 데 사용되는 BCryptPasswordEncoder를 빈으로 정의합니다.
    // 사용자 비밀번호를 데이터베이스에 저장하기 전에 해시화합니다.
    @Bean
    public BCryptPasswordEncoder customPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // 인증되지 않은 사용자가 보호된 리소스에 접근하려고 할 때 호출됩니다.
    // JwtAuthenticationEntryPoint를 통해 처리됩니다.
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    // 인증된 사용자가 허용되지 않은 리소스에 접근하려고 할 때 호출됩니다.
    // JwtAccessDeniedHandler를 통해 처리됩니다.
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new JwtAccessDeniedHandler();
    }

    // 각 요청에 대해 JWT 토큰을 검증하는 필터를 정의합니다.
    // JwtVerifier를 사용하여 토큰의 유효성을 검증합니다.
    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(jwtVerifier);
    }


    // 보안 필터 체인을 정의하고, 여러 가지 보안 설정을 적용합니다.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http
            .cors(cors -> {});
        // H2 DB 헤더 옵션
        http
            .headers(headers ->
                headers.addHeaderWriter(new XFrameOptionsHeaderWriter(
                    XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)
                )
            );
        http.exceptionHandling(
            exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler())
        );
        http.sessionManagement(
            sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        http.authorizeHttpRequests(
            authorize -> authorize
                .requestMatchers(request -> request.getRequestURI().startsWith("/swagger-ui")).permitAll()
                .requestMatchers(request -> request.getRequestURI().startsWith("/v3/api-docs")).permitAll()
                .requestMatchers(request -> request.getRequestURI().startsWith("/auth/sign-up")).permitAll()
                .requestMatchers(request -> request.getRequestURI().startsWith("/auth/sign-in")).permitAll()
                .requestMatchers(request -> request.getRequestURI().startsWith("/dev/ping")).permitAll()
                .requestMatchers(request -> request.getRequestURI().startsWith("/h2-console")).permitAll()
                .anyRequest().authenticated()
        );
        http.addFilterBefore(
            jwtFilter(),
            UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
}