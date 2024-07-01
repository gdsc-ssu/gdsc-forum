package com.example.gdscforum.domain.comment.repository;

import com.example.gdscforum.domain.comment.entity.Comment;
import com.example.gdscforum.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByUserId(Integer userId);
}
