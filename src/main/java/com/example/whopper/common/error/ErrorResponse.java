package com.example.whopper.common.error;

import com.example.whopper.common.error.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Builder
public record ErrorResponse(
        String message,
        int status,
        @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
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
