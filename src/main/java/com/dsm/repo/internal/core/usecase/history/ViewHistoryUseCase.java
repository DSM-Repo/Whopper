package com.dsm.repo.internal.core.usecase.history;

import com.dsm.repo.external.web.rest.history.dto.HistoryResponse;

import java.util.List;

public interface ViewHistoryUseCase {
    List<HistoryResponse> viewAll();
}
