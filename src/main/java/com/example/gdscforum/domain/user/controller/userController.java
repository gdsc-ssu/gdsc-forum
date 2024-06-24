package com.example.gdscforum.domain.user.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.common.security.jwt.JwtService;
import com.example.gdscforum.domain.user.Dto.UserSimpleResponseDto;
import com.example.gdscforum.domain.user.service.RawUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "유저 관련 API")
public class userController {

    private final RawUserService rawUserService;
    private final JwtService jwtService;


    @GetMapping
    public Response<UserSimpleResponseDto> getUser() {

        UserSimpleResponseDto user = rawUserService.getUserById(jwtService.getTokenDto().getUserId());

        return Response.of(200, "조회 성공", user);
    }

}
