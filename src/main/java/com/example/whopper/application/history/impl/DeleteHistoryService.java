package com.example.whopper.application.history.impl;

import com.example.whopper.application.history.usecase.DeleteHistoryUseCase;
import com.example.whopper.domain.history.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DeleteHistoryService implements DeleteHistoryUseCase {
    private final HistoryRepository historyRepository;

    @Override
    public void deleteById(String historyId) {
        historyRepository.deleteById(historyId);
    }
}
