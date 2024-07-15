package com.example.gdscforum.domain.post.service;

import com.example.gdscforum.common.security.jwt.JwtService;
import com.example.gdscforum.domain.post.dto.PostDto;
import com.example.gdscforum.domain.post.entity.Post;
import com.example.gdscforum.domain.post.exception.PostNotFoundException;
import com.example.gdscforum.domain.post.repository.PostRepository;
import com.example.gdscforum.domain.user.entity.User;
import com.example.gdscforum.domain.user.repository.UserRepository;
import com.example.gdscforum.domain.user.service.RawUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// final 멤버가 있기 때문에 @RequiredArgsConstructor를 사용
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final RawUserService rawUserService;
    private final JwtService jwtService;

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
    public PostDto createPost(String title, String content, Integer userId) {
        User user = rawUserService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Post post = new Post(title, content, user);
        post = postRepository.save(post);

        return PostDto.from(post);
    }

    //게시글 수정
    @Transactional
    public PostDto updatePost(Integer id, String title, String content) {
        Integer currentUserId = jwtService.getTokenDto().getUserId(); // 현재 로그인한 유저의 ID를 가져옴

        Post post =  postRepository.findById(id)
            .orElseThrow(PostNotFoundException::new);

        if (!post.getUser().getId().equals(currentUserId)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        post.setTitle(title);
        post.setContent(content);

        postRepository.save(post);

        return PostDto.from(post);
    }

    //게시글 삭제
    @Transactional
    public void deletePost(Integer id) {
        Integer currentUserId = jwtService.getTokenDto().getUserId(); // 현재 로그인한 유저의 ID를 가져옴

        Post post =  postRepository.findById(id)
            .orElseThrow(PostNotFoundException::new);

        if (!post.getUser().getId().equals(currentUserId)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }

        postRepository.delete(post);
    }

    // 특정 사용자가 작성한 게시글 조회
    public List<PostDto> getPostsByUserId(Integer userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        return PostDto.from(posts);
    }

}