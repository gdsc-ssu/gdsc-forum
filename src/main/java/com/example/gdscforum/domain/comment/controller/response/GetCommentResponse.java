package com.example.gdscforum.domain.comment.controller.response;

import com.example.gdscforum.domain.comment.dto.CommentDto;
import com.example.gdscforum.domain.comment.entity.Comment;
import com.example.gdscforum.domain.post.dto.PostDto;
import com.example.gdscforum.domain.user.dto.UserDto;
import com.example.gdscforum.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class GetCommentResponse {
    private Integer id;
    private String content;
    private String createdAt;
    private String updatedAt;

    public static GetCommentResponse from(CommentDto comment) {
        return GetCommentResponse.builder()
            .id(comment.getId())
            .content(comment.getContent())
            .createdAt(comment.getCreatedAt().toString())
            .updatedAt(comment.getUpdatedAt().toString())
            .build();
    }

    public static List<GetCommentResponse> from(List<CommentDto> comments) {
        return comments.stream()
            .map(GetCommentResponse::from)
            .collect(Collectors.toList());
    }
}
