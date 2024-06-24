package com.example.gdscforum.common.security.configuration;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtAlgorithmConfiguration {
    @Bean
    public Algorithm tokenAlgorithm() {
        //원래 이렇게 하드코딩하면 안된다. 환경변수로 빼던지 다른 방법으로 관리해야한다.
        String secret = "gdscforum";
        return Algorithm.HMAC256(secret);
    }
}
