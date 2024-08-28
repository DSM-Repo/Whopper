package com.repo.whopper.application.history.impl;

import com.repo.whopper.application.history.usecase.ViewHistoryUseCase;
import com.repo.whopper.domain.history.HistoryRepository;
import com.repo.whopper.interfaces.history.dto.HistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
class ViewHistoryService implements ViewHistoryUseCase {
    private final HistoryRepository historyRepository;

    @Override
    @Transactional
    public List<HistoryResponse> viewAll() {
        return historyRepository.findAll()
                .map(history -> new HistoryResponse(history.id(), history.date(), history.content()))
                .toList();
    }
}
