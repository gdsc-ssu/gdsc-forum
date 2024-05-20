package com.example.gdscforum.domain.post.service;


import com.example.gdscforum.domain.post.entity.Post;
import com.example.gdscforum.domain.post.entity.dto.PostDto;
import com.example.gdscforum.domain.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);

        return PostDto.from(post);
    }
}
