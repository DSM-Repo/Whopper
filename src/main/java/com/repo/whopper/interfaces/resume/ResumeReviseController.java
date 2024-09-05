package com.repo.whopper.interfaces.resume;

import com.repo.whopper.application.resume.usecase.ResumeReviseUseCase;
import com.repo.whopper.common.annotation.OnlyStudent;
import com.repo.whopper.interfaces.resume.dto.ResumeElementDto;
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
