package com.example.gdscforum.domain.post.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T> {
    private int code;
    private String message;
    private T data;

    public static <T> Response<T> data(T data) {
        return new Response<>(0, "", data);
    }
}