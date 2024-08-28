package com.repo.whopper.domain.refreshtoken;

import java.util.Optional;

public interface RefreshTokenRepository {
    RefreshTokenModel save(RefreshTokenModel refreshTokenModel);
    Optional<RefreshTokenModel> findByToken(String token);
}
