package com.example.gdscforum.common.exception;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class UnauthorizedException extends ApiException {
    public UnauthorizedException(String message) {
        super(UNAUTHORIZED, message);
    }
}
