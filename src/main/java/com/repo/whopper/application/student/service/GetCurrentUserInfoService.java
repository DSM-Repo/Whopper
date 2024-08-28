package com.repo.whopper.application.student.service;

import com.repo.whopper.application.student.usecase.GetCurrentUserInfoUseCase;
import com.repo.whopper.interfaces.student.dto.GetCurrentUserInfoResponse;
import com.repo.whopper.application.student.component.CurrentStudent;
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
