package com.example.gdscforum.domain.post.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.domain.post.controller.request.CreatePostRequest;
import com.example.gdscforum.domain.post.controller.response.GetPostResponse;
import com.example.gdscforum.domain.post.dto.PostDto;
import com.example.gdscforum.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @Operation(
        summary = "게시글 단일 조회",
        description = "게시글의 id로 게시글을 조회합니다."
    )
    @GetMapping("/{id}")
    public Response<GetPostResponse> getPostById(@PathVariable Integer id) {
        PostDto post = postService.getPostById(id);

        return Response.data(GetPostResponse.from(post));
    }

    @Operation(
        summary = "게시글 전체 조회",
        description = "모든 게시글을 조회합니다."
    )
    @GetMapping
    public Response<List<GetPostResponse>> listPosts() {
        List<PostDto> posts = postService.listPosts();

        return Response.data(GetPostResponse.from(posts));
    }

    @Operation(
        summary = "게시글 생성",
        description = "게시글을 생성합니다."
    )
    @PostMapping
    public Response<GetPostResponse> createPost(@Valid @RequestBody CreatePostRequest request) {
        PostDto post = postService.createPost(request.getTitle(), request.getContent());

        return Response.data(GetPostResponse.from(post));
    }
}
