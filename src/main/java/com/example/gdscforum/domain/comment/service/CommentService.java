package com.example.gdscforum.domain.comment.service;

import com.example.gdscforum.domain.comment.dto.CommentDto;
import com.example.gdscforum.domain.comment.entity.Comment;
import com.example.gdscforum.domain.comment.exception.CommentNotFoundException;
import com.example.gdscforum.domain.comment.repository.CommentRepository;
import com.example.gdscforum.domain.post.dto.PostDto;
import com.example.gdscforum.domain.post.entity.Post;
import com.example.gdscforum.domain.post.exception.PostNotFoundException;
import com.example.gdscforum.domain.post.repository.PostRepository;
import com.example.gdscforum.domain.post.service.PostService;
import com.example.gdscforum.domain.post.service.RawPostService;
import com.example.gdscforum.domain.user.entity.User;
import com.example.gdscforum.domain.user.service.RawUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final RawUserService rawUserService;
    private final RawPostService rawPostService;
    private final PostService postService;

    @Transactional
    public CommentDto createComment(String content, Integer postId, Integer userId) {
        User user = rawUserService.getUserById(userId);
        Post post = rawPostService.getPostById(postId);

        Comment comment = new Comment(content, user, post, LocalDateTime.now(), LocalDateTime.now());
        comment = commentRepository.save(comment);

        return CommentDto.from(comment);
    }

    @Transactional
    public CommentDto updateComment(Integer id, String content) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(CommentNotFoundException::new);

        comment.setContent(content);
        comment.setUpdatedAt(LocalDateTime.now());

        commentRepository.save(comment);

        return CommentDto.from(comment);
    }

    @Transactional
    public void deleteComment(Integer id) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(CommentNotFoundException::new);

        commentRepository.delete(comment);
    }

    public List<PostDto> getMyCommentPosts(Integer userId) {
        List<Comment> comments = commentRepository.findAllByUserId(userId);
        List<Integer> postIds = comments.stream()
            .map(Comment::getPost)
            .map(Post::getId)
            .distinct()
            .toList();

        return postService.listPostsByIds(postIds);
    }
}