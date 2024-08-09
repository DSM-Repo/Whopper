package com.example.whopper.domain.document.application.usecase;

import com.example.whopper.domain.document.dto.request.UpdateActivityElementRequest;

import java.util.List;

public interface UpdateActivityListUseCase {
    void update(List<UpdateActivityElementRequest> request);
}
