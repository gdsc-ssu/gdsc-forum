package com.example.gdscforum.domain.user.exception;


import com.example.gdscforum.common.exception.BadRequestException;

public class UserNotFoundException extends BadRequestException {

    private static final String MESSAGE = "존재하지 않는 사용자입니다.";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
