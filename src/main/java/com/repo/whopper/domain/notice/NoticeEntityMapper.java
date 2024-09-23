package com.repo.whopper.domain.notice;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NoticeEntityMapper {
    NoticeModel toModel(NoticeEntity entity);
    NoticeEntity toEntity(NoticeModel model);
}
