package com.example.whopper.domain.refreshtoken;

import com.example.whopper.interfaces.auth.dto.AuthElementDto;

public record RefreshTokenModel(
        String id,
        AuthElementDto.UserRole role,
        String token,
        Long timeToLive
) {
}
