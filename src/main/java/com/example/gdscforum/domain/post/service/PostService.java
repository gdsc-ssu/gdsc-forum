package com.example.gdscforum.domain.post.service;

import com.example.gdscforum.domain.post.dto.PostDto;
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

    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(Post::toDto)
                .collect(Collectors.toList());
    }

    public Optional<PostDto> getPostById(Integer id) {
        return postRepository.findById(id)
                .map(Post::toDto);
    }

    public PostDto createPost(PostDto postDTO) {
        Post post = postDTO.toEntity();
        Post savedPost = postRepository.save(post);
        return savedPost.toDto();
    }

    public Optional<PostDto> updatePost(Integer id, PostDto postDTO) {
        return postRepository.findById(id)
                .map(existingPost -> {
                    existingPost.setTitle(postDTO.getTitle());
                    existingPost.setContent(postDTO.getContent());
                    Post updatedPost = postRepository.save(existingPost);
                    return updatedPost.toDto();
                });
    }

    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }
}