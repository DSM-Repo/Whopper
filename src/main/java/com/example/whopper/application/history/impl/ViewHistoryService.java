package com.example.whopper.application.history.impl;

import com.example.whopper.application.history.usecase.ViewHistoryUseCase;
import com.example.whopper.domain.history.HistoryRepository;
import com.example.whopper.interfaces.history.dto.HistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ViewHistoryService implements ViewHistoryUseCase {
    private final HistoryRepository historyRepository;

    @Override
    public List<HistoryResponse> viewAll() {
        return historyRepository.findAll()
                .map(history -> new HistoryResponse(history.id(), history.date(), history.content()))
                .toList();
    }
}
