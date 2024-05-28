package com.example.gdscforum.domain.post.application.service;

import com.example.gdscforum.domain.post.persistence.entity.PostEntity;
import com.example.gdscforum.domain.post.persistence.repository.PostJpaRepository;
import com.example.gdscforum.domain.post.presentation.request.PostCreateRequest;
import com.example.gdscforum.domain.post.presentation.response.PostCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostCreateService {
	// FIXME: jpa와의 강결합
	private final PostJpaRepository postJpaRepository;

	@Transactional
	public PostCreateResponse create(PostCreateRequest request) {
		PostEntity toSave = PostEntity.of(request);

		PostEntity result = postJpaRepository.save(toSave);

		return PostCreateResponse.of(result);
	}
}
