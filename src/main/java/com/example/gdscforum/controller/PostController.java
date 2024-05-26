package com.example.gdscforum.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.controller.response.GetPostResponse;
import com.example.gdscforum.entity.dto.PostDto;
import com.example.gdscforum.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@Tag(name = "게시글 조회", description = "프로젝트 1주차 api 테스트")
public class PostController {
    private final PostService postService;

    @Operation(
            summary = "단일 게시글 search",
            responses = {
                    @ApiResponse(responseCode = "200", description = "ok"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @GetMapping("/search/{id}")
    public Response<GetPostResponse> get(@PathVariable("id") Integer ID) {
        PostDto postDto = postService.getPostById(ID);
        return Response.data(GetPostResponse.from(postDto));
    }
}
