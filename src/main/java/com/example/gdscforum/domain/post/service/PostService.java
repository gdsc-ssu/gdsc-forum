package com.example.gdscforum.domain.post.service;

import com.example.gdscforum.domain.post.entity.dto.PostDto;
import com.example.gdscforum.domain.post.entity.Post;
import com.example.gdscforum.domain.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public PostDto getPostById(Long id) {
        Post postEntity = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));
        return PostDto.from(postEntity);
    }

    @Transactional
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostDto createPost(PostDto postDto) {
        Post postEntity = PostDto.toEntity(postDto);
        postEntity.setCreatedAt(LocalDateTime.now());
        postEntity.setUpdatedAt(LocalDateTime.now());
        Post savedPost = postRepository.save(postEntity);
        return PostDto.from(savedPost);
    }
}
