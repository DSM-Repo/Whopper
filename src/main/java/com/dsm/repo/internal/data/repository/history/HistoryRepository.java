package com.dsm.repo.internal.data.repository.history;

import com.dsm.repo.internal.core.domain.model.history.HistoryModel;

import java.util.stream.Stream;

public interface HistoryRepository {
    void save(HistoryModel model);
    void deleteById(String historyId);
    Stream<HistoryModel> findAll();
}
