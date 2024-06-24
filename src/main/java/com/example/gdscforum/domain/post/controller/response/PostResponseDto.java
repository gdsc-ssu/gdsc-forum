package com.example.gdscforum.domain.post.controller.response;

import com.example.gdscforum.domain.post.entity.Post;
import com.example.gdscforum.domain.user.Dto.UserSimpleResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class PostResponseDto {

    private Integer id;

    private String title;

    private String content;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private UserSimpleResponseDto userResponseDto;

    @Builder
    public PostResponseDto(Integer id, String title, String content, Timestamp createdAt, Timestamp updatedAt, UserSimpleResponseDto userResponseDto) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userResponseDto = userResponseDto;
    }

    public static PostResponseDto fromEntity(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .userResponseDto(UserSimpleResponseDto.fromEntity(post.getUser()))
                .build();
    }

}
