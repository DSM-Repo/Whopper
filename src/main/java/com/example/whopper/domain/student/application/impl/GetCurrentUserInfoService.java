package com.example.whopper.domain.student.application.impl;

import com.example.whopper.domain.student.application.usecase.GetCurrentUserInfoUseCase;
import com.example.whopper.domain.student.dto.GetCurrentUserInfoResponse;
import com.example.whopper.global.utils.current.CurrentStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCurrentUserInfoService implements GetCurrentUserInfoUseCase {
    private final CurrentStudent currentStudent;

    @Override
    public GetCurrentUserInfoResponse get() {
        var currentStudent = this.currentStudent.getStudent();
        return GetCurrentUserInfoResponse.fromStudentEntity(
                currentStudent,
                currentStudent.getMajor()
        );
    }
}
