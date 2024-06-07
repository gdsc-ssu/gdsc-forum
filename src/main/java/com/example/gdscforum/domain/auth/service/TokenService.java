package com.example.gdscforum.domain.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.gdscforum.domain.auth.dto.AuthTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final Algorithm tokenAlgorithm;
    @Value("${app.jwt.accessTokenValidMS}") private Long accessTokenValidMilliseconds;
    @Value("${app.jwt.refreshTokenValidMS}") private Long refreshTokenValidMilliseconds;


    private AuthTokenDto createToken(Integer id, String role, Long tokenValidMilliseconds) {
        Date expiredAt = new Date(System.currentTimeMillis() + tokenValidMilliseconds);

        String token = JWT.create()
            .withClaim("userId", id)
            .withClaim("userRole", role)
            .withExpiresAt(expiredAt)
            .sign(tokenAlgorithm);

        return new AuthTokenDto(token, tokenValidMilliseconds);
    }

    public AuthTokenDto createAccessToken(Integer id, String role) {
        return createToken(id, role, accessTokenValidMilliseconds);
    }

    public AuthTokenDto createRefreshToken(Integer id, String role) {
        return createToken(id, role, refreshTokenValidMilliseconds);
    }
}


