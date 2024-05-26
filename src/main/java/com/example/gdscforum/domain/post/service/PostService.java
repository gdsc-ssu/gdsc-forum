package com.example.gdscforum.domain.post.service;

import com.example.gdscforum.domain.post.controller.response.PostResponseDto;
import com.example.gdscforum.domain.post.entity.Post;
import com.example.gdscforum.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostResponseDto> findAllPosts() {
        return postRepository.findAll().stream()
                .map(PostResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<PostResponseDto> findPostById(Long postId) {
        return postRepository.findById(postId).map(PostResponseDto::fromEntity);
    }

    public PostResponseDto createPost(Post post) {
        Post savedPost = postRepository.save(post);
        return PostResponseDto.fromEntity(savedPost);
    }
}
