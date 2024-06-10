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

    @Bean
    public BCryptPasswordEncoder customPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new JwtAccessDeniedHandler();
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(jwtVerifier);
    }

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