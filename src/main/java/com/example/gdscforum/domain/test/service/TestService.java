package com.example.gdscforum.domain.test.service;

import com.example.gdscforum.domain.test.dto.TestDto;
import com.example.gdscforum.domain.test.entity.PostEntity;
import com.example.gdscforum.domain.test.repository.TestRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    @Transactional
    public TestDto get(Long id) {
        PostEntity postEntity = testRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));
        return new TestDto(postEntity);
    }


}
