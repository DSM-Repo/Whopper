package com.example.whopper.interfaces.student;

import com.example.whopper.application.student.usecase.GetCurrentUserInfoUseCase;
import com.example.whopper.interfaces.student.dto.GetCurrentUserInfoResponse;
import com.example.whopper.common.annotation.OnlyStudent;
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
