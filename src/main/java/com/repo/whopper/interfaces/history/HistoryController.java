package com.repo.whopper.interfaces.history;

import com.repo.whopper.application.history.usecase.CreateHistoryUseCase;
import com.repo.whopper.application.history.usecase.DeleteHistoryUseCase;
import com.repo.whopper.application.history.usecase.ViewHistoryUseCase;
import com.repo.whopper.common.annotation.OnlyTeacher;
import com.repo.whopper.common.http.dto.DataResponseInfo;
import com.repo.whopper.interfaces.history.dto.HistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/history")
class HistoryController {
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
    
    record CreateHistoryRequest(String date, String content) {}
}
