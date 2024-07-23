package com.example.gdscforum.domain.post.exception;


import com.example.gdscforum.common.exception.BadRequestException;

public class PostNotFoundException extends BadRequestException {

    private static final String MESSAGE = "존재하지 않는 게시글입니다..";

    public PostNotFoundException() {
        super(MESSAGE);
    }
}
