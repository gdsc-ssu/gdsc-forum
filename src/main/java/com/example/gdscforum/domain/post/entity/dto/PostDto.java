package com.example.gdscforum.domain.post.entity.dto;

import com.example.gdscforum.domain.post.entity.Post;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostDto {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public static PostDto from(Post post) {
        return PostDto.builder() //빌더 패턴
            .title(post.getTitle())
            .content(post.getContent())
            .createdAt(post.getCreatedAt())
            .updatedAt(post.getUpdatedAt())
            .build();
    }
}

