package com.example.whopper.domain.document.application.usecase;

import com.example.whopper.domain.document.domain.element.ProjectElement;

import java.util.List;

public interface UpdateProjectListUseCase {
    void update(List<ProjectElement> request);
}
