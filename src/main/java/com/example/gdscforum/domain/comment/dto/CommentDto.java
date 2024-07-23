package com.example.gdscforum.domain.comment.dto;

import com.example.gdscforum.domain.comment.entity.Comment;
import com.example.gdscforum.domain.post.entity.Post;
import com.example.gdscforum.domain.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@Builder
public class CommentDto {

    private Integer id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CommentDto from(Comment comment) {
        return CommentDto.builder()
            .id(comment.getId())
            .content(comment.getContent())
            .createdAt(comment.getCreatedAt())
            .updatedAt(comment.getUpdatedAt())
            .build();
    }

    public static List<CommentDto> from(List<Comment> comments) {
        return comments.stream()
            .map(CommentDto::from)
            .collect(Collectors.toList());
    }
}
