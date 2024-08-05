package com.example.whopper.domain.auth.application.usecase;

import com.example.whopper.domain.auth.dto.request.LoginRequest;
import com.example.whopper.domain.auth.dto.response.TokenResponse;

public interface TeacherLoginUseCase {
    TokenResponse teacherLogin(LoginRequest request);
}
