package com.example.gdscforum.domain.post.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.domain.post.controller.request.PostCreateRequest;
import com.example.gdscforum.domain.post.controller.response.PostResponseDto;
import com.example.gdscforum.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Post 컨트롤러", description = "게시글 관련 API")
public class PostController {
    private final PostService postService;

    @Operation(
            summary = "전체 게시글 조회",
            responses = @ApiResponse(responseCode = "200", description = "게시글 전체를 반환합니다.")
    )
    @GetMapping
    public Response<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> posts = postService.findAllPosts();
        return Response.of(200, "조회 성공", posts);
    }

    @Operation(
            summary = "특정 게시글 반환",
            responses = @ApiResponse(responseCode = "200", description = "특정 게시글을 반환합니다")
    )
    @GetMapping("/{postId}")
    public Response<PostResponseDto> getPostById(@PathVariable Long postId) {
        PostResponseDto post = postService.findPostById(postId).orElseThrow(() -> new RuntimeException(("Post not found with id " + postId)));
        return Response.of(200, "조회 성공", post);
    }

    @Operation(
            summary = "게시글 생성",
            responses = @ApiResponse(responseCode = "201", description = "게시글을 새로 생성합니다")
    )
    @PostMapping
    public Response<PostResponseDto> createPost(@RequestBody PostCreateRequest request) {
        PostResponseDto postResponseDto = postService.createPost(request);
        return Response.of(201, "생성 성공", postResponseDto);

    }

    //patch는 DB의 특정값만 바꾸고 싶을 때 사용한다. (부분 수정 )
    @PutMapping("/{id}")
    public Response<PostResponseDto> update(@PathVariable Long id, @Valid @RequestBody PostCreateRequest request) {
        PostResponseDto post = postService.update(id, request.getTitle(), request.getContent());

        return Response.of(200, "수정 성공", post);
    }


    //삭제 메서드는 보통 응답을 안 주더라
    //그리고 soft delete를 쓴다.
    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable Long id) {
        postService.deletePost(id);
        return Response.data("OK");
    }
}
