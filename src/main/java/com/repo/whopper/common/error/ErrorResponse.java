package com.repo.whopper.common.error;

import com.repo.whopper.common.error.exception.ErrorCode;
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
        String description,
        String exception
) {
    public static ErrorResponse of(ErrorCode errorCode, String description, Exception e) {
        return ErrorResponse.builder()
                .message(errorCode.getMessage())
                .status(errorCode.getStatusCode())
                .timestamp(LocalDateTime.now())
                .description(description)
                .exception(e.getClass().getSimpleName())
                .build();
    }

    public static ErrorResponse of(int statusCode, String description, Exception e) {
        return ErrorResponse.builder()
                .message(description)
                .status(statusCode)
                .timestamp(LocalDateTime.now())
                .description(description)
                .exception(e.getClass().getSimpleName())
                .build();
    }
}
