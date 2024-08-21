package com.example.whopper.lagacy.document.application.usecase;

import com.example.whopper.lagacy.document.dto.ProjectElementDto;

import java.util.List;

public interface UpdateProjectListUseCase {
    void update(List<ProjectElementDto> request);
}
