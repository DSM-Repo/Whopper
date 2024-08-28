package com.repo.whopper.application.auth.usecase;

import com.repo.whopper.interfaces.auth.dto.request.LoginRequest;
import com.repo.whopper.interfaces.auth.dto.response.TokenResponse;

public interface TeacherLoginUseCase {
    TokenResponse teacherLogin(LoginRequest request);
}