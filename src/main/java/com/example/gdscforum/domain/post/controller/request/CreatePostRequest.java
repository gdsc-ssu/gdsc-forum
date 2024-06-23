package com.example.gdscforum.domain.post.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreatePostRequest {
    @NotEmpty
    private String title;

    @NotEmpty
    private String content;
}
