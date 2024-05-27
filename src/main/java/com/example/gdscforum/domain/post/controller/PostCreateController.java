package com.example.gdscforum.domain.post.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.domain.post.controller.request.CreatePostRequest;
import com.example.gdscforum.domain.post.controller.response.CreatePostResponse;
import com.example.gdscforum.domain.post.controller.response.GetPostResponse;
import com.example.gdscforum.domain.post.entity.dto.PostDto;
import com.example.gdscforum.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Tag(name = "게시글 작성", description = "프로젝트 2주차")
public class PostCreateController {
    private final PostService postService;

    @Operation(
            summary = "게시글 작성",
            responses = {
                    @ApiResponse(responseCode = "200", description = "ok"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @PostMapping
    public Response<CreatePostResponse> createPost(@Valid @RequestBody CreatePostRequest createPostRequest) {
        PostDto postDto = PostDto.fromCreateRequest(createPostRequest);
        PostDto createdPost = postService.createPost(postDto);
        return Response.data(CreatePostResponse.from(createdPost));
    }

    @Operation(
            summary = "게시글 수정",
            responses = {
                    @ApiResponse(responseCode = "200", description = "ok"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @PutMapping("/{id}")
    public Response<GetPostResponse> updatePost(@PathVariable("id") Long id, @Valid @RequestBody CreatePostRequest request){
        PostDto post = postService.updatePost(id, request.getTitle(), request.getContent());
        return Response.data(GetPostResponse.from(post));
    }

    @Operation(
            summary = "게시글 삭제",
            responses = {
                    @ApiResponse(responseCode = "200", description = "ok"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @DeleteMapping("/{id}")
    public Response<String> updatePost(@PathVariable("id") Long id){
        postService.deletePost(id);
        return Response.data("ok");
    }

}
