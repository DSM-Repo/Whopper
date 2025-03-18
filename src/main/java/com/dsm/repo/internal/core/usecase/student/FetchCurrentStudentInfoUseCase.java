package com.dsm.repo.internal.core.usecase.student;

import com.dsm.repo.external.web.rest.student.dto.CurrentStudentInfoResponse;

public interface FetchCurrentStudentInfoUseCase {
    CurrentStudentInfoResponse fetch();
}
