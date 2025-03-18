package com.dsm.repo.internal.data.repository.history;

import com.dsm.repo.internal.core.domain.model.history.HistoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HistoryEntityMapper {
    HistoryModel toModel(HistoryEntity entity);
    HistoryEntity toEntity(HistoryModel model);
}
