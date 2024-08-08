package com.example.whopper.domain.auth.application.usecase;

import com.example.whopper.domain.auth.dto.response.TokenResponse;

public interface ReissueTokenUseCase {
    TokenResponse reissueToken(String token);
}
