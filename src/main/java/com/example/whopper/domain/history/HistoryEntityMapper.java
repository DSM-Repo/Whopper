package com.example.whopper.domain.history;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface HistoryEntityMapper {
    HistoryModel toModel(HistoryEntity entity);
    HistoryEntity toEntity(HistoryModel model);
}
