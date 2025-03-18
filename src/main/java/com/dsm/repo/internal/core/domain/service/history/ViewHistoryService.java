package com.dsm.repo.internal.core.domain.service.history;

import com.dsm.repo.internal.core.usecase.history.ViewHistoryUseCase;
import com.dsm.repo.internal.data.repository.history.HistoryRepository;
import com.dsm.repo.external.web.rest.history.dto.HistoryResponse;
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
