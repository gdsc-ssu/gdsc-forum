package com.example.gdscforum.domain.post.presentation.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.domain.post.application.service.PostDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostDeleteController {
	private final PostDeleteService postDeleteService;

	@DeleteMapping("/{id}")
	public Response<String> deletePost(@PathVariable Long id) {
		postDeleteService.delete(id);

		return Response.data("OK");
	}
}
