package com.example.gdscforum.domain.user.Dto;


import com.example.gdscforum.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSimpleResponseDto {

    private String username;
    private String email;

    @Builder
    public UserSimpleResponseDto(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public static UserSimpleResponseDto fromEntity(User user) {
        return UserSimpleResponseDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

}
