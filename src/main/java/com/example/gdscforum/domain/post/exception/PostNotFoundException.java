package com.example.gdscforum.domain.post.exception;

import com.example.gdscforum.common.exception.NotFoundException;

public class PostNotFoundException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 Post 입니다.";

    public PostNotFoundException() {
        super(MESSAGE);
    }
}
