package com.example.gdscforum.domain.post.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {
    @NotBlank
    @Schema(description = "제목")
    private String title;

    @NotBlank
    @Schema(description = "내용")
    private String content;
}