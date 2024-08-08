package com.example.whopper.domain.student.api;

import com.example.whopper.domain.student.application.usecase.GetCurrentUserInfoUseCase;
import com.example.whopper.domain.student.dto.GetCurrentUserInfoResponse;
import com.example.whopper.global.annotation.OnlyStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class StudentController {
    private final GetCurrentUserInfoUseCase getCurrentUserInfoUseCase;

    @OnlyStudent
    @GetMapping("/current/info")
    public GetCurrentUserInfoResponse getCurrentUserInfoResponse() {
        return getCurrentUserInfoUseCase.get();
    }
}
