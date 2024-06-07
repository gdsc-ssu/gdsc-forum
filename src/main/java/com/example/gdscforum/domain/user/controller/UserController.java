package com.example.gdscforum.domain.user.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.domain.user.controller.response.GetUserResponse;
import com.example.gdscforum.domain.user.dto.UserDto;
import com.example.gdscforum.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Operation(
        summary = "유저 정보 조회",
        description = "ID로 유저의 정보를 조회합니다."
    )
    @GetMapping("/{id}")
    public Response<GetUserResponse> getPostById(@PathVariable Integer id) {
        UserDto user = userService.getUserById(id);

        return Response.data(GetUserResponse.from(user));
    }
}
