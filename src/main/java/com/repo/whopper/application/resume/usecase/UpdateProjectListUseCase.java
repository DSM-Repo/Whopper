package com.repo.whopper.application.resume.usecase;

import com.repo.whopper.interfaces.resume.dto.ResumeElementDto;

import java.util.List;

public interface UpdateProjectListUseCase {
    void update(List<ResumeElementDto.Project> request);
}