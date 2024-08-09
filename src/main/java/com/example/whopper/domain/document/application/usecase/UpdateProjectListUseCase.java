package com.example.whopper.domain.document.application.usecase;

import com.example.whopper.domain.document.dto.request.UpdateProjectElementRequest;

import java.util.List;

public interface UpdateProjectListUseCase {
    void update(List<UpdateProjectElementRequest> request);
}
