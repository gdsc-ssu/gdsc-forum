package com.example.gdscforum.domain.post.dto;

import com.example.gdscforum.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostDto {

    private Long id;
    private String title;
    private String content;

    @Builder
    public PostDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // DTO to Entity 변환 메서드
    public static Post toEntity(PostDto postDto) {
        return Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build();
    }
}
