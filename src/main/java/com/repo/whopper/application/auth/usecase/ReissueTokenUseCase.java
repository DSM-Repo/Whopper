package com.repo.whopper.application.auth.usecase;

import com.repo.whopper.interfaces.auth.dto.response.TokenResponse;

public interface ReissueTokenUseCase {
    TokenResponse reissueToken(String token);
}
