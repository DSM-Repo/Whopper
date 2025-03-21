package com.dsm.repo.internal.data.repository.history;

import org.springframework.data.repository.Repository;

import java.util.stream.Stream;

interface HistoryDataRepository extends Repository<HistoryEntity, String> {
    Stream<HistoryEntity> findAll();
    void save(HistoryEntity entity);
    void deleteById(String historyId);
}
