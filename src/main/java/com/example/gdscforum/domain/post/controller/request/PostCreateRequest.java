package com.example.gdscforum.domain.post.controller.request;

import com.example.gdscforum.domain.post.entity.Post;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreateRequest {

    @NotBlank
    private final String title;
    @NotBlank
    private final String content;

    @Builder
    public PostCreateRequest(@NotBlank String title, @NotBlank String content) {
        this.title = title;
        this.content = content;
    }

    // 요청 객체를 엔티티로 변환하는 static 메서드
    public static Post toEntity(PostCreateRequest request) {
        return Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }


}
