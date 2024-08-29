package com.repo.whopper.application.student.service;

import com.repo.whopper.application.student.usecase.FetchCurrentStudentInfoUseCase;
import com.repo.whopper.interfaces.student.dto.CurrentStudentInfoResponse;
import com.repo.whopper.application.student.component.CurrentStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FetchCurrentStudentInfoService implements FetchCurrentStudentInfoUseCase {
    private final CurrentStudent currentStudent;

    @Override
    public CurrentStudentInfoResponse fetch() {
        final var currentStudent = this.currentStudent.getStudent();

        return CurrentStudentInfoResponse.fromStudentModel(
                currentStudent
        );
    }
}
