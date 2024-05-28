package com.example.gdscforum.domain.post.persistence.entity;

import com.example.gdscforum.common.entity.BaseTimeEntity;
import com.example.gdscforum.domain.post.presentation.request.PostCreateRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "post")
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

	public void update(
		String title,
		String content
	) {
		if (title != null && content != null) {
			this.title = title;
			this.content = content;
		}
	}
}
