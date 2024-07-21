package com.example.whopper.domain.auth.application.usecase;

import com.example.whopper.domain.auth.dto.request.StudentLoginRequest;
import com.example.whopper.domain.auth.dto.response.TokenResponse;

public interface StudentLoginUseCase {
    TokenResponse studentLogin(StudentLoginRequest studentLoginRequest);
}
