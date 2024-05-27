package com.example.gdscforum.domain.post.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.domain.post.controller.response.GetPostResponse;
import com.example.gdscforum.domain.post.entity.dto.PostDto;
import com.example.gdscforum.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Tag(name = "게시글 조회", description = "프로젝트 1주차")
public class PostController {
    private final PostService postService;

    @Operation(
            summary = "단일 게시글 search",
            responses = {
                    @ApiResponse(responseCode = "200", description = "ok"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @GetMapping("/search/{id}")
    public Response<GetPostResponse> getPosts(@PathVariable("id") Long id) {
        PostDto postDto = postService.getPostById(id);
        return Response.data(GetPostResponse.from(postDto));
    }

    @Operation(
            summary = "전체 게시글 search",
            responses = {
                    @ApiResponse(responseCode = "200", description = "ok"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @GetMapping("/search")
    public Response<List<GetPostResponse>> getAllPosts() {
        List<PostDto> postDtos = postService.getAllPosts();
        List<GetPostResponse> responses = postDtos.stream()
                .map(GetPostResponse::from)
                .collect(Collectors.toList());
        return Response.data(responses);
    }
}
