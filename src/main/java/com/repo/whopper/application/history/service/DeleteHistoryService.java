package com.repo.whopper.application.history.service;

import com.repo.whopper.application.history.usecase.DeleteHistoryUseCase;
import com.repo.whopper.domain.history.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class DeleteHistoryService implements DeleteHistoryUseCase {
    private final HistoryRepository historyRepository;

    @Override
    @Transactional
    public void deleteById(String historyId) {
        historyRepository.deleteById(historyId);
    }
}
