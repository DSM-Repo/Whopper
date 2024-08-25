package com.example.whopper.domain.history;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HistoryEntityMapper {
    HistoryModel toModel(HistoryEntity entity);
    HistoryEntity toEntity(HistoryModel model);
}
