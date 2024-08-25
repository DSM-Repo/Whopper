package com.example.whopper.application.history.impl;

import com.example.whopper.application.history.usecase.CreateHistoryUseCase;
import com.example.whopper.domain.history.HistoryModel;
import com.example.whopper.domain.history.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CreateHistoryService implements CreateHistoryUseCase {
    private final HistoryRepository historyRepository;

    @Override
    public void create(String date, String content) {
        final var newModel = new HistoryModel(date, content);

        historyRepository.save(newModel);
    }
}
