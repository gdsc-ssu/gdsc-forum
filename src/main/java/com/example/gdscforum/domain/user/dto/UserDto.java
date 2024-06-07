package com.example.gdscforum.domain.user.dto;

import com.example.gdscforum.domain.post.entity.Post;
import com.example.gdscforum.domain.user.entity.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer id;
    private String username;
    private String email;
    private String introduction;
    private Integer age;
    private String link;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserDto from(User user) {
        return UserDto.builder()
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

    public static List<UserDto> from(List<User> users) {
        return users.stream()
            .map(UserDto::from)
            .collect(Collectors.toList());
    }
}
