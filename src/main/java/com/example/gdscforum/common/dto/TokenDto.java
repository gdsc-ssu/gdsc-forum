package com.example.gdscforum.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenDto {
    private Integer userId;
    private String userRole;

    public static TokenDto of(Integer userId, String userRole) {
        return new TokenDto(userId, userRole);
    }
}