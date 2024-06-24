package com.example.gdscforum.domain.user.Dto;


import com.example.gdscforum.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDto {

    private String username;
    private String email;

    @Builder
    public UserResponseDto(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public static UserResponseDto fromEntity(User user) {
        return UserResponseDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

}
