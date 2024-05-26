package com.example.gdscforum.controller.response;

import com.example.gdscforum.entity.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class GetPostResponse {
    private int id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static GetPostResponse from(PostDto postDto) {
        return GetPostResponse.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .content(postDto.getTitle())
                .createdAt(postDto.getCreatedAt())
                .updatedAt(postDto.getUpdatedAt())
                .build();
    }
}
