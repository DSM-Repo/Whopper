package com.example.whopper.domain.auth.api;

import com.example.whopper.domain.auth.application.usecase.StudentLoginUseCase;
import com.example.whopper.domain.auth.dto.request.StudentLoginRequest;
import com.example.whopper.domain.auth.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final StudentLoginUseCase studentLoginUseCase;

    @PostMapping("/student")
    public TokenResponse studentLogin(@RequestBody StudentLoginRequest request) {
        return studentLoginUseCase.studentLogin(request);
    }
}
