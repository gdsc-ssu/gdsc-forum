package com.example.gdscforum.domain.user.dto;

import com.example.gdscforum.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Integer id;
    private String username;
    private String email;
    private String introduction;
    private Integer age;
    private String link;
    private String role;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .introduction(user.getIntroduction())
                .age(user.getAge())
                .link(user.getLink())
                .role(user.getRole())
                .build();
    }
}
