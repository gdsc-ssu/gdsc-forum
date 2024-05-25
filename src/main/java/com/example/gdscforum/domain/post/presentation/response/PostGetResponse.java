package com.example.gdscforum.domain.post.presentation.response;

import com.example.gdscforum.domain.post.persistence.entity.PostEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PostGetResponse {
	private Long id;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	// FIXME: Jpa 엔티티와의 강결합
	public static PostGetResponse of(PostEntity post) {
		return PostGetResponse.builder()
				.id(post.getId())
				.title(post.getTitle())
				.content(post.getContent())
				.createdAt(post.getCreatedAt())
				.updatedAt(post.getUpdatedAt())
				.build();
	}
}
