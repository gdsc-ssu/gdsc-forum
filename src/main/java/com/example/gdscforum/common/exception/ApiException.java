package com.example.gdscforum.common.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public abstract class ApiException extends ResponseStatusException {
    private final String message;

    public ApiException(HttpStatusCode status, String message) {
        super(status);
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
