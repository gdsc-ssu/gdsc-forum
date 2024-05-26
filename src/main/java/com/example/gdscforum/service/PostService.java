package com.example.gdscforum.service;

import com.example.gdscforum.entity.dto.PostDto;
import com.example.gdscforum.entity.Post;
import com.example.gdscforum.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public PostDto getPostById(Integer id) {
        Post postEntity = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));
        return PostDto.from(postEntity);

    }


}
