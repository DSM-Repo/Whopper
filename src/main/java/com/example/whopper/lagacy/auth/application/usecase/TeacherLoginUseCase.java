package com.example.whopper.lagacy.auth.application.usecase;

import com.example.whopper.lagacy.auth.dto.request.LoginRequest;
import com.example.whopper.lagacy.auth.dto.response.TokenResponse;

public interface TeacherLoginUseCase {
    TokenResponse teacherLogin(LoginRequest request);
}
