package com.example.gdscforum.domain.post.controller.response;

import com.example.gdscforum.domain.post.entity.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class CreatePostResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CreatePostResponse from(PostDto postDto) {
        return CreatePostResponse.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .content(postDto.getTitle())
                .createdAt(postDto.getCreatedAt())
                .updatedAt(postDto.getUpdatedAt())
                .build();
    }
}
