package com.example.gdscforum.domain.post.persistence.entity;

import com.example.gdscforum.common.entity.BaseTimeEntity;
import com.example.gdscforum.domain.post.presentation.request.PostCreateRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final Long id = null;

	private String title;
	private String content;

	public static PostEntity of(PostCreateRequest request) {
		return PostEntity.builder()
				.title(request.getTitle())
				.content(request.getContent())
				.build();
	}
}
