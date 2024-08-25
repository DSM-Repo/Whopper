package com.example.whopper.application.resume.usecase;

import com.example.whopper.interfaces.resume.dto.ResumeElementDto;

import java.util.List;

public interface UpdateProjectListUseCase {
    void update(List<ResumeElementDto.Project> request);
}
