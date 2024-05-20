package com.example.gdscforum.domain.test.dto;

import com.example.gdscforum.domain.test.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class TestDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TestDto (PostEntity postEntity) {
        this.id = postEntity.getId();
        this.title = postEntity.getTitle();
        this.content = postEntity.getContent();
        this.createdAt = postEntity.getCreatedAt();
        this.updatedAt = postEntity.getUpdatedAt();
    }

}
