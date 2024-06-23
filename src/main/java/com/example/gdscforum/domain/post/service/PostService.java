package com.example.gdscforum.domain.post.service;

import com.example.gdscforum.domain.post.controller.request.CreatePostRequest;
import com.example.gdscforum.domain.post.controller.request.UpdatePostRequest;
import com.example.gdscforum.domain.post.dto.PostResponse;
import com.example.gdscforum.domain.post.entity.Post;
import com.example.gdscforum.domain.post.exception.PostNotFoundException;
import com.example.gdscforum.domain.post.repository.PostRepository;
import com.example.gdscforum.domain.user.entity.User;
import com.example.gdscforum.domain.user.service.RawUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final RawUserService rawUserService;

    @Transactional
    public PostResponse createPost(CreatePostRequest request, Integer userId) {
        User user = rawUserService.getUserById(userId);
        Post post = new Post(request.getTitle(), request.getContent(), user);

        post = postRepository.save(post);

        return PostResponse.from(post);
    }

    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);

        return PostResponse.from(post);
    }

    public List<PostResponse> getAllPost() {
        List<Post> posts = postRepository.findAll();

        return PostResponse.from(posts);
    }

    @Transactional
    public PostResponse updatePost(Long id, UpdatePostRequest request) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        return PostResponse.from(post);
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);

        postRepository.delete(post);
    }
}
