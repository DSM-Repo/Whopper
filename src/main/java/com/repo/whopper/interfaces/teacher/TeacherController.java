package com.repo.whopper.interfaces.teacher;

import com.repo.whopper.application.teacher.usecase.GetCurrentTeacherInfoUseCase;
import com.repo.whopper.common.annotation.OnlyTeacher;
import com.repo.whopper.interfaces.teacher.dto.GetCurrentTeacherInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
class TeacherController {
    private final GetCurrentTeacherInfoUseCase getCurrentTeacherInfoUseCase;

    @OnlyTeacher
    @GetMapping
    GetCurrentTeacherInfoResponse getCurrentTeacherInfo() {
        return getCurrentTeacherInfoUseCase.get();
    }
}
