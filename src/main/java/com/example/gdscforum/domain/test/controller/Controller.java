package com.example.gdscforum.domain.test.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.domain.test.controller.response.TestResponse;
import com.example.gdscforum.domain.test.dto.TestDto;
import com.example.gdscforum.domain.test.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
@Tag(name = "프로젝트 셋업", description = "프로젝트 1주차 api 테스트")
public class Controller {
    private final TestService testService;

    @Operation(
            summary = "단일 게시글 search",
            responses = {
                    @ApiResponse(responseCode = "200", description = "ok"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @GetMapping("/search/{id}")
    public Response<TestResponse> get(@NotBlank @PathVariable("id") Long ID) {
        TestDto testDto = testService.get(ID);
        return Response.data(TestResponse.from(testDto));
    }
}
