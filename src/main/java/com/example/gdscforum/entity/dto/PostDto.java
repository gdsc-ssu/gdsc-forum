package com.example.gdscforum.entity.dto;

import com.example.gdscforum.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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

}
