package com.dsm.repo.internal.core.domain.service.student;

import com.dsm.repo.internal.core.usecase.student.FetchCurrentStudentInfoUseCase;
import com.dsm.repo.external.web.rest.student.dto.CurrentStudentInfoResponse;
import com.dsm.repo.internal.core.domain.component.student.CurrentStudent;
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
