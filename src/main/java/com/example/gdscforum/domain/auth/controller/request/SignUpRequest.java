package com.example.gdscforum.domain.auth.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    @NotBlank
    @Schema(description = "사용자 이름")
    private String username;

    @NotBlank
    @Schema(description = "이메일")
    private String email;

    @NotBlank
    @Schema(description = "비밀번호")
    private String password;

    @Schema(description = "자기소개")
    private String introduction;

    @Schema(description = "나이")
    private Integer age;

    @Schema(description = "링크")
    private String link;

    @NotBlank
    @Schema(description = "역할")
    private String role;
}
