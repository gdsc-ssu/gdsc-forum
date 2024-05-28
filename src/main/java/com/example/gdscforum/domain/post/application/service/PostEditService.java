package com.example.gdscforum.domain.post.application.service;

import com.example.gdscforum.domain.post.application.exception.PostNotFoundException;
import com.example.gdscforum.domain.post.persistence.entity.PostEntity;
import com.example.gdscforum.domain.post.persistence.repository.PostJpaRepository;
import com.example.gdscforum.domain.post.presentation.request.PostEditRequest;
import com.example.gdscforum.domain.post.presentation.response.PostEditResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostEditService {
	private final PostJpaRepository postJpaRepository;

	@Transactional
	public PostEditResponse edit(Long id, PostEditRequest request) {
		PostEntity toEdit = postJpaRepository.findById(id)
				.orElseThrow(PostNotFoundException::new);

		toEdit.update(request.getTitle(), request.getContent());

		return PostEditResponse.of(toEdit);
	}
}
