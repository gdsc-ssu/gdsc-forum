package com.example.gdscforum.domain.post.dto;

import com.example.gdscforum.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@Builder
public class PostDto {

    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PostDto from(Post post) {
        return PostDto.builder()
            .id(post.getId())
            .title(post.getTitle())
            .content(post.getContent())
            .createdAt(post.getCreatedAt())
            .updatedAt(post.getUpdatedAt())
            .build();
    }

    public static List<PostDto> from(List<Post> posts) {
        return posts.stream()
            .map(PostDto::from)
            .collect(Collectors.toList());
    }
}
