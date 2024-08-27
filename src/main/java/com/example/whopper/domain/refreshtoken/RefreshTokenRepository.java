package com.example.whopper.domain.refreshtoken;

import java.util.Optional;

public interface RefreshTokenRepository {
    Optional<RefreshTokenModel> findByToken(String token);
}
