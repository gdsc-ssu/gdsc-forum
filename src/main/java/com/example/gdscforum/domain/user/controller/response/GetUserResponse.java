package com.example.gdscforum.domain.user.controller.response;

import com.example.gdscforum.domain.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class GetUserResponse {
    private Integer id;
    private String username;
    private String email;
    private String introduction;
    private Integer age;
    private String link;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static GetUserResponse from(UserDto userDto) {
        return GetUserResponse.builder()
            .id(userDto.getId())
            .username(userDto.getUsername())
            .email(userDto.getEmail())
            .introduction(userDto.getIntroduction())
            .age(userDto.getAge())
            .link(userDto.getLink())
            .role(userDto.getRole())
            .createdAt(userDto.getCreatedAt())
            .updatedAt(userDto.getUpdatedAt())
            .build();
    }
}
