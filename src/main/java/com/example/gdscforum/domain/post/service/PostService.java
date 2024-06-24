package com.example.gdscforum.domain.post.service;

import com.example.gdscforum.domain.post.controller.request.PostCreateRequest;
import com.example.gdscforum.domain.post.controller.response.PostResponseDto;
import com.example.gdscforum.domain.post.entity.Post;
import com.example.gdscforum.domain.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public List<PostResponseDto> findAllPosts() {
        return postRepository.findAll().stream()
                .map(PostResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NotFoundException("Post not found with id " + postId));
        return PostResponseDto.fromEntity(post);
    }

    @Transactional
    public PostResponseDto createPost(PostCreateRequest request) {
        Post post = PostCreateRequest.toEntity(request);
        Post savedPost = postRepository.save(post);
        return PostResponseDto.fromEntity(savedPost);
    }


    //파라미터로 모델을 넣는다.
    @Transactional
    public PostResponseDto update(Long id, String title, String content) {
        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("post 조회 실패"));

        //post에 update 함수 만들어서 null이 아닌 값만 update해주는 방식으로
        post.setTitle(title);
        post.setContent(content);
        post.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        //save를 안넣어도 jpa가 알아서 필드 값이 바뀐 것을 인지하고 update하고 save를 해준다.

        return PostResponseDto.fromEntity(post);
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("post 조회 실패"));

        postRepository.delete(post);

    }
}
