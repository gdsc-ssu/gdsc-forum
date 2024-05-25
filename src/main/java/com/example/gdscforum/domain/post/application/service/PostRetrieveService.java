package com.example.gdscforum.domain.post.application.service;

import com.example.gdscforum.domain.post.presentation.response.PostGetResponse;
import com.example.gdscforum.domain.post.persistence.entity.PostEntity;
import com.example.gdscforum.domain.post.persistence.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostRetrieveService {
	// FIXME: jpa와의 강결합
	private final PostJpaRepository postJpaRepository;

	public PostGetResponse getById(Long id) {
		// FIXME: jpa 엔티티와의 강결합
		PostEntity resultEntity = postJpaRepository.findById(id).orElseThrow();

		return PostGetResponse.of(resultEntity);
	}

	public List<PostGetResponse> getAll() {
		// FIXME: jpa 엔티티와의 강결합
		List<PostEntity> resultEntities = postJpaRepository.findAll();

		return resultEntities.stream()
				.map(PostGetResponse::of)
				.collect(Collectors.toList());
	}
}
