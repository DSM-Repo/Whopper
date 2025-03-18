package com.dsm.repo.external.web.rest.teacher;

import com.dsm.repo.internal.core.usecase.teacher.GetCurrentTeacherInfoUseCase;
import com.dsm.repo.external.security.auth.annotation.OnlyTeacher;
import com.dsm.repo.external.web.rest.teacher.dto.GetCurrentTeacherInfoResponse;
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
