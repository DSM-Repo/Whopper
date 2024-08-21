package com.example.whopper.application.auth.service;

import com.example.whopper.application.auth.usecase.ReissueTokenUseCase;
import com.example.whopper.domain.refreshtoken.RefreshTokenEntity;
import com.example.whopper.domain.refreshtoken.RefreshTokenRepository;
import com.example.whopper.interfaces.auth.dto.response.TokenResponse;
import com.example.whopper.common.exception.auth.RefreshTokenNotFoundException;
import com.example.whopper.common.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ReissueTokenService implements ReissueTokenUseCase {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public TokenResponse reissueToken(String token) {
        RefreshTokenEntity refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(()-> RefreshTokenNotFoundException.EXCEPTION);

        return jwtTokenProvider.receiveToken(refreshToken.getId(), refreshToken.getUserRole());
    }
}
