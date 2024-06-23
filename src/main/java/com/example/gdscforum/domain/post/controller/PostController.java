package com.example.gdscforum.domain.post.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.common.dto.TokenDto;
import com.example.gdscforum.domain.post.controller.request.CreatePostRequest;
import com.example.gdscforum.domain.post.controller.request.UpdatePostRequest;
import com.example.gdscforum.domain.post.dto.PostResponse;
import com.example.gdscforum.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public Response<PostResponse> createPost(@AuthenticationPrincipal TokenDto tokenDto,
                                             @Valid @RequestBody CreatePostRequest request) {
        PostResponse postResponse = postService.createPost(request, tokenDto.getUserId());
        return Response.data(postResponse);
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
    public Response<PostResponse> getPost(@NotNull @PathVariable("post_id") Long id) {
        PostResponse postResponse = postService.getPost(id);
        return Response.data(postResponse);
    }

    @Operation(
            summary = "Post 전체 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @GetMapping
    public Response<List<PostResponse>> getAllPost() {
        List<PostResponse> postResponses = postService.getAllPost();
        return Response.data(postResponses);
    }

    @Operation(
            summary = "Post 수정",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "POST_NOT_FOUND"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @PutMapping("/{post_id}")
    public Response<PostResponse> updatePost(@NotNull @PathVariable("post_id") Long id,
                                             @Valid @RequestBody UpdatePostRequest request) {
        PostResponse postResponse = postService.updatePost(id, request);
        return Response.data(postResponse);
    }

    @Operation(
            summary = "Post 삭제",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "POST_NOT_FOUND"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @DeleteMapping("/{post_id}")
    public Response<String> deletePost(@NotNull @PathVariable("post_id") Long id) {
        postService.deletePost(id);

        return Response.data("OK");
    }

    @Operation(
            summary = "내가 작성한 게시글 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @GetMapping("/my")
    public Response<List<PostResponse>> getMyPost(@AuthenticationPrincipal TokenDto tokenDto) {
        List<PostResponse> postResponse = postService.getMyPost(tokenDto.getUserId());
        return Response.data(postResponse);
    }
}
