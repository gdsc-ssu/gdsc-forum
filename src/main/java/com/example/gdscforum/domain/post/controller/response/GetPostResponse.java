package com.example.gdscforum.domain.post.controller.response;

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
public class GetPostResponse {
    private Integer id;
    private String title;
    private String content;
    private UserDto user;
    private String createdAt;
    private String updatedAt;

    public static GetPostResponse from(PostDto postDto) {
        return GetPostResponse.builder()
            .id(postDto.getId())
            .title(postDto.getTitle())
            .content(postDto.getContent())
            .user(postDto.getUser())
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
