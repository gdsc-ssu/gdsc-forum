package com.example.gdscforum.domain.user.exception;

import com.example.gdscforum.common.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 User 입니다.";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
