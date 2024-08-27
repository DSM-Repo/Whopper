package com.example.whopper.application.student.impl;

import com.example.whopper.application.student.usecase.GetCurrentUserInfoUseCase;
import com.example.whopper.interfaces.student.dto.GetCurrentUserInfoResponse;
import com.example.whopper.application.student.component.CurrentStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCurrentUserInfoService implements GetCurrentUserInfoUseCase {
    private final CurrentStudent currentStudent;

    @Override
    public GetCurrentUserInfoResponse get() {
        final var currentStudent = this.currentStudent.getStudent();

        return GetCurrentUserInfoResponse.fromStudentEntity(
                currentStudent
        );
    }
}
