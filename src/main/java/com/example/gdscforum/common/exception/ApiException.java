package com.example.gdscforum.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class ApiException extends ResponseStatusException {

    private final String message;

    public ApiException(final HttpStatus status, final String message) {
        super(status, message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}