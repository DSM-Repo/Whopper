package com.dsm.repo.internal.core.domain.service.auth;

import com.dsm.repo.internal.core.usecase.auth.ReissueTokenUseCase;
import com.dsm.repo.external.exception.domain.auth.InvalidTokenException;
import com.dsm.repo.internal.core.domain.model.auth.RefreshTokenModel;
import com.dsm.repo.internal.data.repository.refreshtoken.RefreshTokenRepository;
import com.dsm.repo.external.web.rest.auth.dto.response.TokenResponse;
import com.dsm.repo.external.security.jwt.JwtTokenProvider;
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
