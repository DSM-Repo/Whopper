package com.example.whopper.application.auth.usecase;

import com.example.whopper.interfaces.auth.dto.response.TokenResponse;

public interface ReissueTokenUseCase {
    TokenResponse reissueToken(String token);
}
