package com.example.gdscforum.domain.post.application.service;

import com.example.gdscforum.domain.post.application.exception.PostNotFoundException;
import com.example.gdscforum.domain.post.persistence.entity.PostEntity;
import com.example.gdscforum.domain.post.persistence.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostDeleteService {
	private final PostJpaRepository postJpaRepository;

	@Transactional
	public void delete(Long id) {
		PostEntity toDelete = postJpaRepository.findById(id)
				.orElseThrow(PostNotFoundException::new);

		postJpaRepository.delete(toDelete);
	}
}
