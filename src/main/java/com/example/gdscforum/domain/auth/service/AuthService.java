package com.example.gdscforum.domain.auth.service;

import com.example.gdscforum.common.exception.BadRequestException;
import com.example.gdscforum.common.security.jwt.JwtService;
import com.example.gdscforum.common.util.PasswordEncoder;
import com.example.gdscforum.domain.auth.controller.response.SignResponse;
import com.example.gdscforum.domain.auth.dto.AuthTokenDto;
import com.example.gdscforum.domain.user.entity.User;
import com.example.gdscforum.domain.user.service.RawUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final RawUserService rawUserService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignResponse signUp(String username, String email, String password, String introduction, Integer age, String link, String role) {
        Optional<User> existingUser = rawUserService.getOptionalUserByEmail(email);

        if (existingUser.isPresent()) {
            throw new BadRequestException("이미 가입된 이메일입니다.");
        }

        password = passwordEncoder.encode(password);
        User user = rawUserService.createUser(username, email, password, introduction, age, link, role);

        AuthTokenDto accessToken = jwtService.createAccessToken(user.getId(), role);
        AuthTokenDto refreshToken = jwtService.createRefreshToken(user.getId(), role);

        user.setRefreshToken(refreshToken.getToken());

        return SignResponse.of(accessToken, refreshToken);
    }

    public SignResponse signIn(String email, String password) {
        User user = rawUserService.getOptionalUserByEmail(email)
            .orElseThrow(() -> new BadRequestException("가입되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException("비밀번호가 일치하지 않습니다.");
        }

        AuthTokenDto accessToken = jwtService.createAccessToken(user.getId(), user.getRole());
        AuthTokenDto refreshToken = jwtService.createRefreshToken(user.getId(), user.getRole());

        user.setRefreshToken(refreshToken.getToken());

        return SignResponse.of(accessToken, refreshToken);
    }
}
