package com.repo.whopper.interfaces.auth.controller;

import com.repo.whopper.application.auth.usecase.ReissueTokenUseCase;
import com.repo.whopper.application.auth.usecase.StudentLoginUseCase;
import com.repo.whopper.application.auth.usecase.TeacherLoginUseCase;
import com.repo.whopper.interfaces.auth.dto.request.LoginRequest;
import com.repo.whopper.interfaces.auth.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
class AuthController {
    private final StudentLoginUseCase studentLoginUseCase;
    private final TeacherLoginUseCase teacherLoginUseCase;
    private final ReissueTokenUseCase reissueTokenUseCase;

    @PostMapping("/student")
    TokenResponse studentLogin(@RequestBody LoginRequest request) {
        return studentLoginUseCase.studentLogin(request);
    }

    @PostMapping("/teacher")
    TokenResponse teacherLogin(@RequestBody LoginRequest request) {
        return teacherLoginUseCase.teacherLogin(request);
    }

    @PutMapping("/token")
    TokenResponse reissueToken(@RequestHeader(name = "x-refresh-token") String token) {
        return reissueTokenUseCase.reissueToken(token);
    }
}
