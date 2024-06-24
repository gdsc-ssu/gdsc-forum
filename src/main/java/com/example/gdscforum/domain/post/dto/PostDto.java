package com.example.gdscforum.domain.post.dto;

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

}
