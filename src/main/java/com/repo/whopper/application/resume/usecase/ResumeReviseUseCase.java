package com.repo.whopper.application.resume.usecase;

import com.repo.whopper.interfaces.resume.dto.ResumeElementDto;

public interface ResumeReviseUseCase {
    void revise(ResumeElementDto.ReviseRequest request);
}
