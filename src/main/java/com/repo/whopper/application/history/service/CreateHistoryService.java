package com.repo.whopper.application.history.service;

import com.repo.whopper.application.history.usecase.CreateHistoryUseCase;
import com.repo.whopper.domain.history.HistoryModel;
import com.repo.whopper.domain.history.HistoryRepository;
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
