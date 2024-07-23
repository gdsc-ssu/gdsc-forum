package com.example.gdscforum.domain.post.service;

import com.example.gdscforum.domain.post.dto.PostDto;
import com.example.gdscforum.domain.post.entity.Post;
import com.example.gdscforum.domain.post.exception.PostNotFoundException;
import com.example.gdscforum.domain.post.repository.PostRepository;
import com.example.gdscforum.domain.user.entity.User;
import com.example.gdscforum.domain.user.service.RawUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

// final 멤버가 있기 때문에 @RequiredArgsConstructor를 사용
@RequiredArgsConstructor
@Service
public class RawPostService {
    private final PostRepository postRepository;

    public Post getPostById(Integer id) {
        return postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
    }
}