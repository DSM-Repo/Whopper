package com.example.whopper.application.teacher.impl;

import com.example.whopper.application.teacher.component.TeacherComponent;
import com.example.whopper.application.teacher.usecase.GetCurrentTeacherInfoUseCase;
import com.example.whopper.interfaces.teacher.dto.GetCurrentTeacherInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCurrentTeacherInfoService implements GetCurrentTeacherInfoUseCase {
    private final TeacherComponent teacherComponent;

    @Override
    public GetCurrentTeacherInfoResponse get() {
        final var teacher = teacherComponent.currentTeacher();

        return new GetCurrentTeacherInfoResponse(teacher.name());
    }
}
