package com.example.gdscforum.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
public class AuthTokenDto {
    private String token;
    private Long expiredIn;

    public static AuthTokenDto of(String token, Long expiredIn) {
        return AuthTokenDto.builder()
                .token(token)
                .expiredIn(expiredIn)
                .build();
    }
}
