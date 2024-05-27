package com.example.gdscforum.domain.post.presentation.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.domain.post.application.service.PostEditService;
import com.example.gdscforum.domain.post.presentation.request.PostEditRequest;
import com.example.gdscforum.domain.post.presentation.response.PostEditResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostEditController {
	private final PostEditService postEditService;

	@PutMapping("/{id}")
	public Response<PostEditResponse> updatePost(
		@PathVariable Long id,
		@RequestBody PostEditRequest request
	) {
		PostEditResponse result = postEditService.edit(id, request);
		return Response.of(200, "수정 완료", result);
	}
}
