package com.repo.whopper.application.history.usecase;

import com.repo.whopper.interfaces.history.dto.HistoryResponse;

import java.util.List;

public interface ViewHistoryUseCase {
    List<HistoryResponse> viewAll();
}
