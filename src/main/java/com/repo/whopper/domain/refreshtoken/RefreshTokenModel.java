package com.repo.whopper.domain.refreshtoken;

import com.repo.whopper.interfaces.auth.dto.AuthElementDto;

public record RefreshTokenModel(
        String id,
        AuthElementDto.UserRole role,
        String token,
        Long timeToLive
) {
}
