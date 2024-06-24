package com.example.gdscforum.domain.post.controller.request;

import com.example.gdscforum.domain.post.entity.Post;
import com.example.gdscforum.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreateRequest {


    private final String content;
    private final String title;

    @Builder
    public PostCreateRequest(@NotBlank String title, @NotBlank String content) {
        this.title = title;
        this.content = content;
    }

    // 요청 객체를 엔티티로 변환하는 static 메서드
    public static Post toEntity(PostCreateRequest request, User user) {
        return Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .build();
    }


}
