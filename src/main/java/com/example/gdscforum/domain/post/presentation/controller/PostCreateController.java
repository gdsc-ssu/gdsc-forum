package com.example.gdscforum.domain.post.presentation.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.domain.post.application.service.PostCreateService;
import com.example.gdscforum.domain.post.presentation.request.PostCreateRequest;
import com.example.gdscforum.domain.post.presentation.response.PostCreateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostCreateController {
	private final PostCreateService postCreateService;

	@PostMapping
	@Operation(
		summary = "게시글 생성",
		description = "게시글을 생성합니다.",
		responses = @ApiResponse(responseCode = "200", description = "성공적으로 저장된 게시글입니다.")
	)
	public Response<PostCreateResponse> createPost(
		@RequestBody PostCreateRequest request
	) {
		PostCreateResponse result = postCreateService.create(request);

		return Response.of(200, "게시글 생성 성공", result);
	}
}
