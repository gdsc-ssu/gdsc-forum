package com.example.gdscforum.domain.user.dto;

import com.example.gdscforum.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserResponse {
    private Integer id;
    private String username;
    private String email;
    private String introduction;
    private Integer age;
    private String link;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .introduction(user.getIntroduction())
                .age(user.getAge())
                .link(user.getLink())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
