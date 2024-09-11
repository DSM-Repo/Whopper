package com.repo.whopper.interfaces.student;

import com.repo.whopper.application.student.usecase.FetchCurrentStudentInfoUseCase;
import com.repo.whopper.common.swagger.student.FetchStudentApiDocumentation;
import com.repo.whopper.interfaces.student.dto.CurrentStudentInfoResponse;
import com.repo.whopper.common.annotation.OnlyStudent;
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
