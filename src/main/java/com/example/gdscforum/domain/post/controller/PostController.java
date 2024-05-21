package com.example.gdscforum.domain.post.controller;

import com.example.gdscforum.domain.post.entity.dto.PostDto;
import com.example.gdscforum.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
@Tag(name = "게시글 조회", description = "게시글 조회 API")
public class PostController {

    final PostService postService;

    @Operation(
        summary = "Id로 조회하기",
        responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "302", description = "FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
        }
    )
    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable("id") Long id) {

        return postService.getPostById(id);
    }
}

