package com.example.kindergarten.exception;

import com.example.kindergarten.dto.ApiErrorDto;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({ ConstraintViolationException.class })
    public final ResponseEntity<ApiErrorDto> handleValidationException(ValidationException ex) {
        ApiErrorDto body = ApiErrorDto.builder()
                .errorDate(LocalDateTime.now())
                .message(ex.getMessage())
                .build();

        return ResponseEntity.badRequest().body(body);
    }


}
