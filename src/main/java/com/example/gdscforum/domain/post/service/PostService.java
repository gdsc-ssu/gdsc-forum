package com.example.gdscforum.domain.post.service;


import com.example.gdscforum.domain.post.dto.PostDto;
import com.example.gdscforum.domain.post.entity.Post;
import com.example.gdscforum.domain.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public PostDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return PostDto.from(post);
    }

    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostDto::from).collect(Collectors.toList());
    }

    public PostDto createPost(PostDto postDto) {
        Post post = postRepository.save(Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build());
        return PostDto.from(post);
    }
}
