package com.example.gdscforum.domain.post.controller.response;

import static lombok.AccessLevel.PRIVATE;

import com.example.gdscforum.domain.post.entity.Post;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class GetPostResponse {

    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static GetPostResponse from(Post post) {
        return GetPostResponse.builder()
            .title(post.getTitle())
            .content(post.getContent())
            .createdAt(post.getCreatedAt())
            .updatedAt(post.getUpdatedAt())
            .build();
    }
}

//@Getter
//@AllArgsConstructor
//@Builder(access = PRIVATE)
//public class ShortUrlResponse {
//    private String shortUrl;
//    private String originUrl;
//    private LocalDateTime createdAt;
//
//
//}
