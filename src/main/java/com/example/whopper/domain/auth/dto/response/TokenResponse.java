package com.example.whopper.domain.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TokenResponse(
        String accessToken,
        String refreshToken,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", locale = "Asia/Seoul", timezone = "Asia/Seoul")
        LocalDateTime accessExpiredAt,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", locale = "Asia/Seoul", timezone = "Asia/Seoul")
        LocalDateTime refreshExpiredAt
) {
}
