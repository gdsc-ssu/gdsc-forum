package com.example.gdscforum.domain.post.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.domain.post.controller.request.CreatePostRequest;
import com.example.gdscforum.domain.post.dto.PostDto;
import com.example.gdscforum.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
@Tag(name = "Post API", description = "Post CRUD API")
public class PostController {
    private final PostService postService;

    @Operation(
            summary = "Post 생성",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @PostMapping
    public Response<PostDto> createPost(@Valid @RequestBody CreatePostRequest request) {
        PostDto postDto = postService.createPost(request);
        return Response.data(postDto);
    }

    @Operation(
            summary = "Post 단일 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "POST_NOT_FOUND"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @GetMapping("/{post_id}")
    public Response<PostDto> getPost(@NotNull @PathVariable("post_id") Long id) {
        PostDto postDto = postService.getPost(id);
        return Response.data(postDto);
    }
}
