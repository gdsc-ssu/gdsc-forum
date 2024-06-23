package com.example.gdscforum.domain.user.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.common.dto.TokenDto;
import com.example.gdscforum.domain.user.dto.UserResponse;
import com.example.gdscforum.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
@Tag(name = "유저 API", description = "유저 CRUD API")
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "로그인 한 유저 정보 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @GetMapping
    public Response<UserResponse> getUser(@AuthenticationPrincipal TokenDto tokenDto) {
        UserResponse userResponse = userService.getUser(tokenDto.getUserId());

        return Response.data(userResponse);
    }
}
