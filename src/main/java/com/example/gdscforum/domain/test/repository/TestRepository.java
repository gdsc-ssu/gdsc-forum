package com.example.gdscforum.domain.test.repository;

import com.example.gdscforum.domain.test.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestRepository extends JpaRepository<PostEntity, Long>{
    Optional<PostEntity> findById(Long id);
}