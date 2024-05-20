package com.example.gdscforum.domain.test.controller.response;

import com.example.gdscforum.domain.test.dto.TestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class TestResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static TestResponse from(TestDto testDto) {
        return TestResponse.builder()
                .id(testDto.getId())
                .title(testDto.getTitle())
                .content(testDto.getTitle())
                .createdAt(testDto.getCreatedAt())
                .updatedAt(testDto.getUpdatedAt())
                .build();
    }
}
