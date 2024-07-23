package com.example.gdscforum.domain.comment.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.common.security.jwt.JwtService;
import com.example.gdscforum.domain.comment.controller.request.CreateCommentRequest;
import com.example.gdscforum.domain.comment.controller.request.UpdateCommentRequest;
import com.example.gdscforum.domain.comment.controller.response.GetCommentResponse;
import com.example.gdscforum.domain.comment.dto.CommentDto;
import com.example.gdscforum.domain.comment.service.CommentService;
import com.example.gdscforum.domain.post.controller.response.GetPostResponse;
import com.example.gdscforum.domain.post.dto.PostDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final JwtService jwtService;

    @Operation(
        summary = "댓글 작성",
        description = "댓글을 작성합니다."
    )
    @PostMapping("/{postId}")
    public Response<GetCommentResponse> getPostById(@PathVariable Integer postId, @Valid @RequestBody CreateCommentRequest request) {
        Integer userId = jwtService.getTokenDto().getUserId();

        CommentDto comment = commentService.createComment(request.getContent(), postId, userId);

        return Response.data(GetCommentResponse.from(comment));
    }

    @Operation(
        summary = "댓글 수정",
        description = "댓글을 수정합니다."
    )
    @PutMapping("/{id}")
    public Response<GetCommentResponse> updateComment(@PathVariable Integer id, @Valid @RequestBody UpdateCommentRequest request) {
        CommentDto comment = commentService.updateComment(id, request.getContent());

        return Response.data(GetCommentResponse.from(comment));
    }

    @Operation(
        summary = "댓글 삭제",
        description = "댓글을 삭제합니다."
    )
    @DeleteMapping("/{id}")
    public Response<String> deleteComment(@PathVariable Integer id) {

        commentService.deleteComment(id);

        return Response.data("OK");
    }

    @Operation(
        summary = "내가 댓글을 작성한 게시글 조회",
        description = "내가 작성한 댓글의 게시글을 모두 조회합니다."
    )
    @GetMapping("/posts")
    public Response<List<GetPostResponse>> getMyCommentPosts() {
        Integer userId = jwtService.getTokenDto().getUserId();

        List<PostDto> posts = commentService.getMyCommentPosts(userId);

        return Response.data(GetPostResponse.from(posts));
    }
}
