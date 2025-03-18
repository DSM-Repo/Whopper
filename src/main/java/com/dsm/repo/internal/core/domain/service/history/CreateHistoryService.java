package com.dsm.repo.internal.core.domain.service.history;

import com.dsm.repo.internal.core.usecase.history.CreateHistoryUseCase;
import com.dsm.repo.internal.core.domain.model.history.HistoryModel;
import com.dsm.repo.internal.data.repository.history.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class CreateHistoryService implements CreateHistoryUseCase {
    private final HistoryRepository historyRepository;

    @Override
    @Transactional
    public void create(String date, String content) {
        final var newModel = new HistoryModel(date, content);

        historyRepository.save(newModel);
    }
}
