package com.dsm.repo.external.web.rest.student;

import com.dsm.repo.internal.core.usecase.student.FetchCurrentStudentInfoUseCase;
import com.dsm.repo.external.web.documentation.student.FetchStudentApiDocumentation;
import com.dsm.repo.external.web.rest.student.dto.CurrentStudentInfoResponse;
import com.dsm.repo.external.security.auth.annotation.OnlyStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
class FetchStudentController implements FetchStudentApiDocumentation {

    private final FetchCurrentStudentInfoUseCase fetchCurrentStudentInfoUseCase;

    @OnlyStudent
    @GetMapping
    public @Override CurrentStudentInfoResponse fetchCurrentUserInfoResponse() {
        return fetchCurrentStudentInfoUseCase.fetch();
    }

}
