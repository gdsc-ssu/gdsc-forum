package com.example.gdscforum.domain.post.repository;

import com.example.gdscforum.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
