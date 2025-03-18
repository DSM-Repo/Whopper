package com.dsm.repo.internal.core.domain.service.teacher;

import com.dsm.repo.internal.core.domain.event.teacher.CurrentTeacher;
import com.dsm.repo.internal.core.usecase.teacher.GetCurrentTeacherInfoUseCase;
import com.dsm.repo.external.web.rest.teacher.dto.GetCurrentTeacherInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCurrentTeacherInfoService implements GetCurrentTeacherInfoUseCase {
    private final CurrentTeacher currentTeacher;

    @Override
    public GetCurrentTeacherInfoResponse get() {
        final var teacher = currentTeacher.getTeacher();

        return new GetCurrentTeacherInfoResponse(teacher.name());
    }
}
