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

import java.util.List;

// final 멤버가 있기 때문에 @RequiredArgsConstructor를 사용
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    //게시글 단일 조회
    public PostDto getPostById(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        return PostDto.from(post);
    }

    //게시글 전체 조회
    public List<PostDto> listPosts() {
        List<Post> posts = postRepository.findAll();

        return PostDto.from(posts);
    }

    //게시글 생성
    @Transactional
    public PostDto createPost(String title, String content) {
        Post post = new Post(title, content);
        post = postRepository.save(post);

        return PostDto.from(post);
    }

    //게시글 수정
    @Transactional
    public PostDto updatePost(Integer id, String title, String content) {
        Post post =  postRepository.findById(id)
            .orElseThrow(PostNotFoundException::new);

        post.setTitle(title);
        post.setContent(content);

        postRepository.save(post);

        return PostDto.from(post);
    }

    //게시글 삭제
    @Transactional
    public void deletePost(Integer id) {
        Post post =  postRepository.findById(id)
            .orElseThrow(PostNotFoundException::new);

        postRepository.delete(post);
    }
}