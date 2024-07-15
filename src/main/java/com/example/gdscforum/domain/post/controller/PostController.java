package com.example.gdscforum.domain.post.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.common.security.jwt.JwtService;
import com.example.gdscforum.domain.post.controller.request.CreatePostRequest;
import com.example.gdscforum.domain.post.controller.response.GetPostResponse;
import com.example.gdscforum.domain.post.dto.PostDto;
import com.example.gdscforum.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final JwtService jwtService;

    @Operation(
        summary = "게시글 단일 조회",
        description = "게시글의 id로 게시글을 조회합니다.",
        responses = {
                @ApiResponse(responseCode = "200", description = "OK"),
                @ApiResponse(responseCode = "404", description = "POST_NOT_FOUND"),
                @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
        }
    )
    @GetMapping("/{id}")
    public Response<GetPostResponse> getPostById(@PathVariable Integer id) {
        PostDto post = postService.getPostById(id);

        return Response.data(GetPostResponse.from(post));
    }

    @Operation(
        summary = "게시글 전체 조회",
        description = "모든 게시글을 조회합니다.",
        responses = {
                @ApiResponse(responseCode = "200", description = "OK"),
                @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
        }
    )
    @GetMapping
    public Response<List<GetPostResponse>> listPosts() {
        List<PostDto> posts = postService.listPosts();

        return Response.data(GetPostResponse.from(posts));
    }

    @Operation(
        summary = "게시글 생성",
        description = "게시글을 생성합니다.",
        responses = {
                @ApiResponse(responseCode = "200", description = "OK"),
                @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
        }
    )
    @PostMapping
    public Response<GetPostResponse> createPost(@Valid @RequestBody CreatePostRequest request) {
        Integer userId = jwtService.getTokenDto().getUserId();

        PostDto post = postService.createPost(request.getTitle(), request.getContent(), userId);

        return Response.data(GetPostResponse.from(post));
    }

    @Operation(
        summary = "게시글 수정",
        description = "게시글을 수정합니다.",
        responses = {
                @ApiResponse(responseCode = "200", description = "OK"),
                @ApiResponse(responseCode = "404", description = "POST_NOT_FOUND"),
                @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
        }
    )
    @PutMapping("/{id}")
    public Response<GetPostResponse> updatePost(@PathVariable Integer id, @Valid @RequestBody CreatePostRequest request) {
        PostDto post = postService.updatePost(id, request.getTitle(), request.getContent());

        return Response.data(GetPostResponse.from(post));
    }

    @Operation(
        summary = "게시글 삭제",
        description = "게시글을 삭제합니다.",
        responses = {
                @ApiResponse(responseCode = "200", description = "OK"),
                @ApiResponse(responseCode = "404", description = "POST_NOT_FOUND"),
                @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
        }
    )
    @DeleteMapping("/{id}")
    public Response<String> deletePost(@PathVariable Integer id) {
        postService.deletePost(id);

        return Response.data("OK");
    }

    @Operation(
            summary = "내가 작성한 게시글 조회",
            description = "현재 로그인한 유저의 게시글을 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "POST_NOT_FOUND"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @GetMapping("/my")
    public Response<List<GetPostResponse>> myPosts() {

        Integer userId = jwtService.getTokenDto().getUserId();

        List<PostDto> posts = postService.getPostsByUserId(userId);

        return Response.data(GetPostResponse.from(posts));
    }
}
