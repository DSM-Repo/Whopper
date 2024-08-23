package com.example.whopper.application.auth.usecase;

import com.example.whopper.interfaces.auth.dto.request.LoginRequest;
import com.example.whopper.interfaces.auth.dto.response.TokenResponse;

public interface TeacherLoginUseCase {
    TokenResponse teacherLogin(LoginRequest request);
}
