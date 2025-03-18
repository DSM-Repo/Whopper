package com.dsm.repo.internal.core.usecase.resume;

import com.dsm.repo.external.web.rest.resume.dto.ResumeElementDto;

public interface ResumeReviseUseCase {
    void revise(ResumeElementDto.ReviseRequest request);
}
