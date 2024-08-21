package com.example.whopper.lagacy.auth.dto.response;

import lombok.Builder;

@Builder
public record TokenResponse(
        String accessToken,
        String refreshToken,
        Long accessExpiredAt,
        Long refreshExpiredAt
) {
}
