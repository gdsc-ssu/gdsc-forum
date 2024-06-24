package com.example.gdscforum.common.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.gdscforum.common.dto.TokenDto;
import com.example.gdscforum.domain.auth.dto.AuthTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtService {

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

    public TokenDto getTokenDto() {
        TokenDto tokenDto = (TokenDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (tokenDto == null) {
            throw new RuntimeException("TokenDto is null");
        }
        return tokenDto;
    }

    public AuthTokenDto createAccessToken(Integer id, String role) {
        return createToken(id, role, accessTokenValidMilliseconds);
    }

    public AuthTokenDto createRefreshToken(Integer id, String role) {
        return createToken(id, role, refreshTokenValidMilliseconds);
    }
}
