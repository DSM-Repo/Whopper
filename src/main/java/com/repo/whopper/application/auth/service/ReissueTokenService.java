package com.repo.whopper.application.auth.service;

import com.repo.whopper.application.auth.usecase.ReissueTokenUseCase;
import com.repo.whopper.common.exception.auth.InvalidTokenException;
import com.repo.whopper.domain.refreshtoken.RefreshTokenModel;
import com.repo.whopper.domain.refreshtoken.RefreshTokenRepository;
import com.repo.whopper.interfaces.auth.dto.response.TokenResponse;
import com.repo.whopper.common.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class ReissueTokenService implements ReissueTokenUseCase {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public TokenResponse reissueToken(String token) {
        RefreshTokenModel refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(()-> InvalidTokenException.EXCEPTION);

        return jwtTokenProvider.receiveToken(refreshToken.id(), refreshToken.role());
    }
}
