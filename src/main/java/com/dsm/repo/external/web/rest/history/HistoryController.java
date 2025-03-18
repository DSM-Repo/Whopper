package com.dsm.repo.external.web.rest.history;

import com.dsm.repo.internal.core.usecase.history.CreateHistoryUseCase;
import com.dsm.repo.internal.core.usecase.history.DeleteHistoryUseCase;
import com.dsm.repo.internal.core.usecase.history.ViewHistoryUseCase;
import com.dsm.repo.external.security.auth.annotation.OnlyTeacher;
import com.dsm.repo.external.web.rest.common.DataResponseInfo;
import com.dsm.repo.external.web.rest.history.dto.HistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/history")
class HistoryController { // TODO: 3/18/25 SRP 적용 필요

    private final CreateHistoryUseCase createHistoryUseCase;

    private final ViewHistoryUseCase viewHistoryUseCase;

    private final DeleteHistoryUseCase deleteHistoryUseCase;

    @OnlyTeacher
    @GetMapping
    DataResponseInfo<HistoryResponse> viewAll() {
        final var result = viewHistoryUseCase.viewAll();

        return DataResponseInfo.of(result);
    }

    @OnlyTeacher
    @PostMapping
    void create(@RequestBody CreateHistoryRequest request) {
        createHistoryUseCase.create(request.date(), request.content());
    }

    @OnlyTeacher
    @DeleteMapping("/{historyId}")
    void deleteById(@PathVariable String historyId) {
        deleteHistoryUseCase.deleteById(historyId);
    }
    
    record CreateHistoryRequest(
            String date,
            String content
    ) {
    }

}
