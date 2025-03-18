package com.dsm.repo.internal.core.domain.model.auth;

import com.dsm.repo.external.web.rest.auth.dto.AuthElementDto;

public record RefreshTokenModel(
        String id,
        AuthElementDto.UserRole role,
        String token,
        Long timeToLive
) {
}
