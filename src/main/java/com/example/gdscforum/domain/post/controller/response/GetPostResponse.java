package com.example.gdscforum.domain.post.controller.response;

import com.example.gdscforum.domain.post.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class GetPostResponse {
    private Integer id;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;

    public static GetPostResponse from(PostDto postDto) {
        return GetPostResponse.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .createdAt(postDto.getCreatedAt().toString())
                .updatedAt(postDto.getUpdatedAt().toString())
                .build();
    }

    public static List<GetPostResponse> from(List<PostDto> posts) {
        return posts.stream()
                .map(GetPostResponse::from)
                .collect(Collectors.toList());
    }
}