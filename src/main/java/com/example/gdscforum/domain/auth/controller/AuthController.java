package com.example.gdscforum.domain.auth.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.domain.auth.controller.request.SignInRequest;
import com.example.gdscforum.domain.auth.controller.request.SignUpRequest;
import com.example.gdscforum.domain.auth.controller.response.SignResponse;
import com.example.gdscforum.domain.auth.service.AuthService;
import com.example.gdscforum.domain.post.controller.response.GetPostResponse;
import com.example.gdscforum.domain.post.dto.PostDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Operation(
        summary = "회원가입",
        description = "회원가입 후 토큰을 반환합니다."
    )
    @PostMapping("/sign-up")
    public Response<SignResponse> signUp(@RequestBody SignUpRequest request) {
        SignResponse response = authService.signUp(request.getUsername(), request.getEmail(), request.getPassword(), request.getIntroduction(), request.getAge(), request.getLink(), request.getRole());

        return Response.data(response);
    }

    @Operation(
        summary = "로그인",
        description = "로그인 후 토큰을 반환합니다."
    )
    @PostMapping("/sign-in")
    public Response<SignResponse> signIn(@RequestBody SignInRequest request) {
        SignResponse response = authService.signIn(request.getEmail(), request.getPassword());

        return Response.data(response);
    }
}
