package com.example.whopper.interfaces.history;

import com.example.whopper.application.history.usecase.CreateHistoryUseCase;
import com.example.whopper.application.history.usecase.DeleteHistoryUseCase;
import com.example.whopper.application.history.usecase.ViewHistoryUseCase;
import com.example.whopper.common.annotation.OnlyTeacher;
import com.example.whopper.interfaces.history.dto.HistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/history")
class HistoryController {
    private final CreateHistoryUseCase createHistoryUseCase;
    private final ViewHistoryUseCase viewHistoryUseCase;
    private final DeleteHistoryUseCase deleteHistoryUseCase;

    @OnlyTeacher
    @GetMapping
    List<HistoryResponse> viewAll() {
        return viewHistoryUseCase.viewAll();
    }

    @OnlyTeacher
    @PostMapping
    void create(@RequestBody CreateHistoryRequest request) {
        createHistoryUseCase.create(request.date(), request.content());
    }

    @OnlyTeacher
    @PutMapping("/del")
    void deleteById(@RequestBody DeleteHistoryRequest request) {
        deleteHistoryUseCase.deleteById(request.id());
    }

    record DeleteHistoryRequest(String id) {}
    record CreateHistoryRequest(String date, String content) {}
}