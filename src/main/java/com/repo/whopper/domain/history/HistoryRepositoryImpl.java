package com.repo.whopper.domain.history;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
class HistoryRepositoryImpl implements HistoryRepository {
    private final HistoryDataRepository historyDataRepository;
    private final HistoryEntityMapper historyEntityMapper;

    @Override
    public void save(HistoryModel model) {
        historyDataRepository.save(historyEntityMapper.toEntity(model));
    }

    @Override
    public void deleteById(String historyId) {
        historyDataRepository.deleteById(historyId);
    }

    @Override
    public Stream<HistoryModel> findAll() {
        return historyDataRepository.findAll()
                .map(historyEntityMapper::toModel);
    }
}
