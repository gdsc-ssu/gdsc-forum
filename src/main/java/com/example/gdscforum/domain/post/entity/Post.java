package com.example.gdscforum.domain.post.entity;

import com.example.gdscforum.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post extends BaseEntity {
    @NotEmpty
    @Column(name = "title", length = 256, nullable = false)
    private String title;

    @NotEmpty
    @Column(name = "content", length = 2048, nullable = false)
    private String content;

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
