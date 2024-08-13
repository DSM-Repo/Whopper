package com.example.whopper.domain.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Builder
public record TokenResponse(
        String accessToken,
        String refreshToken,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "Asia/Seoul", locale = "Asia/Seoul")
        ZonedDateTime accessExpiredAt,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "Asia/Seoul", locale = "Asia/Seoul")
        ZonedDateTime refreshExpiredAt
) {
}
