package com.example.gdscforum.domain.user.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.common.security.jwt.JwtService;
import com.example.gdscforum.domain.user.dto.UserDto;
import com.example.gdscforum.domain.user.entity.User;
import com.example.gdscforum.domain.user.service.RawUserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final RawUserService rawUserService;
    private final JwtService jwtService;

    @Operation(
            summary = "로그인 한 유저 정보 조회",
            description = "현재 로그인 한 유저의 정보를 반환합니다."
    )
    @GetMapping
    public Response<UserDto> getLoggedInUser() {
        // 토큰에서 사용자 ID 추출
        Integer userId = jwtService.getTokenDto().getUserId();
        // 사용자 정보 조회
        User user = rawUserService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        UserDto userDto = UserDto.from(user);

        return Response.data(userDto);
    }
}