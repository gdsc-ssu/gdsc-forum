package com.example.gdscforum.domain.post.dto;

import com.example.gdscforum.domain.post.entity.Post;
import com.example.gdscforum.domain.user.dto.UserResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserResponse user;

    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .user(UserResponse.from(post.getUser()))
                .build();
    }

    public static List<PostResponse> from(List<Post> posts) {
        return posts.stream().map(PostResponse::from).collect(Collectors.toList());
    }
}
