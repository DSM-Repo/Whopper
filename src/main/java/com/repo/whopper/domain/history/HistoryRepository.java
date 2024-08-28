package com.repo.whopper.domain.history;

import java.util.stream.Stream;

public interface HistoryRepository {
    void save(HistoryModel model);
    void deleteById(String historyId);
    Stream<HistoryModel> findAll();
}
