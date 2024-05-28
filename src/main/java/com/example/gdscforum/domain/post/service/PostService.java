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
        Post savedPost = postRepository.save(postEntity);
        return PostDto.from(savedPost);
    }

    @Transactional
    public PostDto updatePost(Long id, String title, String content){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다"));

        //엔티티 사이즈가 커진자면 업데이트 값을 어떻게 관리야해얄지 고려해야 할 점
        post.setTitle(title);
        post.setContent(content);
        post.setUpdatedAt(LocalDateTime.now());

        postRepository.save(post);

        return PostDto.from(post);
    }

    @Transactional
    public void deletePost(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 존재하지 않습니다"));

        postRepository.delete(post);
    }
}
