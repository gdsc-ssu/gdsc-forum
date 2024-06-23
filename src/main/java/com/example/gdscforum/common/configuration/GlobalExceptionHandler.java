package com.example.gdscforum.common.configuration;

import com.example.gdscforum.common.exception.ApiException;
import com.example.gdscforum.common.exception.dto.ExceptionDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionDto> handleException(final Exception e) {
        e.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionDto.of(500, "INTERNAL_SERVER_ERROR", e.getMessage()));
    }

    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<ExceptionDto> handleApiException(final ApiException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(e.getStatusCode())
                .body(ExceptionDto.of(e.getStatusCode().value(), e.getStatusCode().toString(), e.getMessage()));
    }

    // @RequestBody의 파라미터가 유효하지 않을 경우
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ExceptionDto> handleValidationError(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("[");
            builder.append(fieldError.getField());
            builder.append("](은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력된 값 : [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]");
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionDto.of(HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST", builder.toString()));
    }

    // @RequestParam과 @PathVariable의 파라미터가 유효하지 않을 경우
    // entity에 대한 제약조건 검증 어노테이션에서도 발생
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ExceptionDto> handleConstraintViolationError(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();

        StringBuilder builder = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            builder.append("[");
            builder.append(constraintViolation.getPropertyPath().toString());
            builder.append("](은)는 ");
            builder.append(constraintViolation.getMessage());
            builder.append(" 입력된 값 : [");
            builder.append(constraintViolation.getInvalidValue());
            builder.append("]");
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionDto.of(HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST", builder.toString()));
    }
}
