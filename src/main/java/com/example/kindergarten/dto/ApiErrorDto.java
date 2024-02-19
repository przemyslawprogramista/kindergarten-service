package com.example.kindergarten.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiErrorDto {

    private LocalDateTime errorDate;
    private String message;
}
