package com.example.whopper.domain.document.application.usecase;

import com.example.whopper.domain.document.dto.ProjectElementDto;

import java.util.List;

public interface UpdateProjectListUseCase {
    void update(List<ProjectElementDto> request);
}
