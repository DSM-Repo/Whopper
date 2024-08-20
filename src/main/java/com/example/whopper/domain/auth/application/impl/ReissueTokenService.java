package com.example.whopper.domain.auth.application.impl;

import com.example.whopper.domain.auth.application.usecase.ReissueTokenUseCase;
import com.example.whopper.domain.auth.dao.RefreshTokenRepository;
import com.example.whopper.domain.auth.domain.RefreshTokenEntity;
import com.example.whopper.domain.auth.dto.response.TokenResponse;
import com.example.whopper.domain.auth.exception.RefreshTokenNotFoundException;
import com.example.whopper.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ReissueTokenService implements ReissueTokenUseCase {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenResponse reissueToken(String token) {
        RefreshTokenEntity refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(()-> RefreshTokenNotFoundException.EXCEPTION);

        return jwtTokenProvider.receiveToken(refreshToken.getId(), refreshToken.getUserRole());
    }
}
