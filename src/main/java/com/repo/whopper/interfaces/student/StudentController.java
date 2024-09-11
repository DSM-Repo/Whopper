package com.repo.whopper.interfaces.student;

import com.repo.whopper.application.student.usecase.GetCurrentUserInfoUseCase;
import com.repo.whopper.interfaces.student.dto.GetCurrentUserInfoResponse;
import com.repo.whopper.common.annotation.OnlyStudent;
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
