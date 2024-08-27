package com.example.whopper.domain.refreshtoken;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
    private final RefreshCrudTokenRepository refreshCrudTokenRepository;
    private final RefreshTokenEntityMapper refreshTokenEntityMapper;

    public RefreshTokenModel save(RefreshTokenModel refreshToken) {
        final var entity = refreshTokenEntityMapper.toEntity(refreshToken);

        return refreshTokenEntityMapper.toModel(refreshCrudTokenRepository.save(entity));
    }

    public Optional<RefreshTokenModel> findByToken(String token) {
        final var result = refreshCrudTokenRepository.findByToken(token);

        return refreshTokenEntityMapper.toOptionalModel(result);
    }
}
