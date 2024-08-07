package com.example.gdscforum.domain.post.controller.response;

import com.example.gdscforum.domain.comment.dto.CommentDto;
import com.example.gdscforum.domain.post.dto.PostDto;
import com.example.gdscforum.domain.post.entity.Post;
import com.example.gdscforum.domain.user.dto.UserDto;
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
    private UserDto user;
    private List<CommentDto> comments;

    public static GetPostResponse from(PostDto postDto) {
        return GetPostResponse.builder()
            .id(postDto.getId())
            .title(postDto.getTitle())
            .content(postDto.getContent())
            .createdAt(postDto.getCreatedAt().toString())
            .updatedAt(postDto.getUpdatedAt().toString())
            .user(postDto.getUser())
            .comments(postDto.getComments())
            .build();
    }

    public static List<GetPostResponse> from(List<PostDto> posts) {
        return posts.stream()
            .map(GetPostResponse::from)
            .collect(Collectors.toList());
    }
}
