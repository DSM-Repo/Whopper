package com.dsm.repo.external.web.rest.resume;

import com.dsm.repo.internal.core.usecase.resume.ResumeReviseUseCase;
import com.dsm.repo.external.security.auth.annotation.OnlyStudent;
import com.dsm.repo.external.web.rest.resume.dto.ResumeElementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resume")
class ResumeReviseController {

    private final ResumeReviseUseCase resumeReviseUseCase;

    @OnlyStudent
    @PatchMapping
    void revise(@RequestBody ResumeElementDto.ReviseRequest request) {
        resumeReviseUseCase.revise(request);
    }

}
