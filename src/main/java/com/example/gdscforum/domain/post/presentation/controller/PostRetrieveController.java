package com.example.gdscforum.domain.post.presentation.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.domain.post.presentation.response.PostGetResponse;
import com.example.gdscforum.domain.post.application.service.PostRetrieveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostRetrieveController {
	private final PostRetrieveService postRetrieveService;

	@GetMapping("/{id}")
	@Operation(
		summary = "게시글 단일 조회",
		description = "id 파라미터로 게시글을 조회합니다.",
		responses = @ApiResponse(responseCode = "200", description = "해당 id 값을 가진 게시글입니다.")
	)
	public Response<PostGetResponse> getPostById(@PathVariable Long id) {
		PostGetResponse result = postRetrieveService.getById(id);

		return Response.of(200, "조회 성공", result);
	}

	@GetMapping
	@Operation(
		summary = "게시글 전체 조회",
		description = "전체 게시글을 조회합니다.",
		responses = @ApiResponse(responseCode = "200", description = "전체 게시글 리스트입니다.")
	)
	public Response<List<PostGetResponse>> getAllPost() {
		List<PostGetResponse> result = postRetrieveService.getAll();

		return Response.of(200, "조회 성공", result);
	}
}
