package com.example.gdscforum.domain.post.service;

import com.example.gdscforum.domain.post.dto.PostDto;
import com.example.gdscforum.domain.post.entity.Post;
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
public class PostService {
    private final PostRepository postRepository;

    public PostDto getPostById(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        return PostDto.from(post);
    }

    public List<PostDto> listPosts() {
        List<Post> posts = postRepository.findAll();

        return PostDto.from(posts);
    }

    @Transactional
    public PostDto createPost(String title, String content) {
        Post post = new Post(title, content, LocalDateTime.now(), LocalDateTime.now());
        post = postRepository.save(post);

        return PostDto.from(post);
    }

    @Transactional
    public PostDto updatePost(Integer id, String title, String content) {
        Post post =  postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        post.setTitle(title);
        post.setContent(content);
        post.setUpdatedAt(LocalDateTime.now());

        postRepository.save(post);

        return PostDto.from(post);
    }

    @Transactional
    public void deletePost(Integer id) {
        Post post =  postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        postRepository.delete(post);
    }
}