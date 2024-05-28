package com.example.gdscforum.domain.post.presentation.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostEditRequest {
	@NotBlank
	String title;
	@NotBlank
	String content;
}
