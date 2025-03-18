package com.dsm.repo.internal.core.domain.service.history;

import com.dsm.repo.internal.core.usecase.history.DeleteHistoryUseCase;
import com.dsm.repo.internal.data.repository.history.HistoryRepository;
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
