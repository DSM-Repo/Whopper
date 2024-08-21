package com.example.whopper.application.resume.usecase;

import com.example.whopper.interfaces.resume.dto.ProjectElementDto;

import java.util.List;

public interface UpdateProjectListUseCase {
    void update(List<ProjectElementDto> request);
}
