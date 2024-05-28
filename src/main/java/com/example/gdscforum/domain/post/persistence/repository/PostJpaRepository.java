package com.example.gdscforum.domain.post.persistence.repository;

import com.example.gdscforum.domain.post.persistence.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {
}
