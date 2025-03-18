package com.dsm.repo.internal.core.usecase.auth;

import com.dsm.repo.external.web.rest.auth.dto.response.TokenResponse;

public interface ReissueTokenUseCase {
    TokenResponse reissueToken(String token);
}
