package com.example.whopper.global.error;

import com.example.whopper.global.error.exception.ErrorCode;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        String message,
        int status,
        LocalDateTime timestamp,
        String description
) {
    public static ErrorResponse of(ErrorCode errorCode, String description) {
        return ErrorResponse.builder()
                .message(errorCode.getMessage())
                .status(errorCode.getStatusCode())
                .timestamp(LocalDateTime.now())
                .description(description)
                .build();
    }

    public static ErrorResponse of(int statusCode, String description) {
        return ErrorResponse.builder()
                .message(description)
                .status(statusCode)
                .timestamp(LocalDateTime.now())
                .description(description)
                .build();
    }
}
