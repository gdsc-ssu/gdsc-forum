package com.example.gdscforum.domain.post.dto;

import com.example.gdscforum.domain.post.entity.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostDto {

    private Integer id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Content cannot be blank")
    private String content;

    @NotNull(message = "Created at timestamp cannot be null")
    private LocalDateTime createdAt;

    @NotNull(message = "Updated at timestamp cannot be null")
    private LocalDateTime updatedAt;

    public static PostDto fromEntity(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    public Post toEntity() {
        return Post.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}