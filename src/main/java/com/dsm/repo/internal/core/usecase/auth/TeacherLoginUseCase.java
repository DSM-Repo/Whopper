package com.dsm.repo.internal.core.usecase.auth;

import com.dsm.repo.external.web.rest.auth.dto.request.LoginRequest;
import com.dsm.repo.external.web.rest.auth.dto.response.TokenResponse;

public interface TeacherLoginUseCase {
    TokenResponse teacherLogin(LoginRequest request);
}
