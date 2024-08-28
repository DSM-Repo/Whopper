package com.repo.whopper.application.resume.usecase;

import com.repo.whopper.interfaces.resume.dto.ResumeElementDto;

public interface UpdateIntroduceUseCase {
    void update(ResumeElementDto.Introduce request);
}
