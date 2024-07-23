package com.example.gdscforum.domain.comment.exception;


import com.example.gdscforum.common.exception.BadRequestException;

public class CommentNotFoundException extends BadRequestException {

    private static final String MESSAGE = "존재하지 않는 댓글입니다.";

    public CommentNotFoundException() {
        super(MESSAGE);
    }
}
