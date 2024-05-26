package com.example.gdscforum.domain.post.service;

import com.example.gdscforum.domain.post.controller.request.CreatePostRequest;
import com.example.gdscforum.domain.post.dto.PostDto;
import com.example.gdscforum.domain.post.entity.Post;
import com.example.gdscforum.domain.post.exception.PostNotFoundException;
import com.example.gdscforum.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public PostDto createPost(CreatePostRequest request) {
        Post post = new Post(request.getTitle(), request.getContent());

        post = postRepository.save(post);

        return PostDto.from(post);
    }

    public PostDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);

        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getTitle())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
