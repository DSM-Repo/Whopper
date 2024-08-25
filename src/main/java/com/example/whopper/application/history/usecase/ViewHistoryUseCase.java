package com.example.whopper.application.history.usecase;

import com.example.whopper.interfaces.history.dto.HistoryResponse;

import java.util.List;

public interface ViewHistoryUseCase {
    List<HistoryResponse> viewAll();
}
