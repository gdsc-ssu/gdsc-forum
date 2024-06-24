package com.example.gdscforum.domain.auth.controller.response;

import com.example.gdscforum.domain.auth.dto.AuthTokenDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class SignResponse {
    private AuthTokenDto accessToken;
    private AuthTokenDto refreshToken;

    public static SignResponse of(AuthTokenDto accessToken, AuthTokenDto refreshToken) {
        return SignResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}