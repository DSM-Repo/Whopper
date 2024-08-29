package com.repo.whopper.application.student.service;

import com.repo.whopper.application.student.usecase.FetchCurrentUserInfoUseCase;
import com.repo.whopper.interfaces.student.dto.CurrentUserInfoResponse;
import com.repo.whopper.application.student.component.CurrentStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FetchCurrentUserInfoService implements FetchCurrentUserInfoUseCase {
    private final CurrentStudent currentStudent;

    @Override
    public CurrentUserInfoResponse fetch() {
        final var currentStudent = this.currentStudent.getStudent();

        return CurrentUserInfoResponse.fromStudentModel(
                currentStudent
        );
    }
}
