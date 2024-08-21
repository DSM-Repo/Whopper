package com.example.whopper.lagacy.auth.application.usecase;

import com.example.whopper.lagacy.auth.dto.response.TokenResponse;

public interface ReissueTokenUseCase {
    TokenResponse reissueToken(String token);
}
