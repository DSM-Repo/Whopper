package com.dsm.repo.internal.data.repository.refreshtoken;

import com.dsm.repo.internal.core.domain.model.auth.RefreshTokenModel;

import java.util.Optional;

public interface RefreshTokenRepository {
    RefreshTokenModel save(RefreshTokenModel refreshTokenModel);
    Optional<RefreshTokenModel> findByToken(String token);
}
