package com.example.whopper.domain.refreshtoken;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
    private final RefreshTokenCrudRepository refreshTokenCrudRepository;
    private final RefreshTokenEntityMapper refreshTokenEntityMapper;

    @Override
    public Optional<RefreshTokenModel> findByToken(String token) {
        final var result = refreshTokenCrudRepository.findByToken(token);

        return refreshTokenEntityMapper.toOptionalModel(result);
    }
}
