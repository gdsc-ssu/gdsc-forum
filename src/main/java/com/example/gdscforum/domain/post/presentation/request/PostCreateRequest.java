package com.example.gdscforum.domain.post.presentation.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostCreateRequest {
	@NotBlank
	private String title;
	@NotBlank
	private String content;
}
