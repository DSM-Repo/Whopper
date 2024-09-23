package com.repo.whopper.domain.notice;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;
import java.util.stream.Stream;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NoticeEntityMapper {
    NoticeModel toModel(NoticeEntity entity);
    NoticeEntity toEntity(NoticeModel model);

    default Optional<NoticeModel> toOptionalModel(Optional<NoticeEntity> optionalEntity) {
        return optionalEntity.map(this::toModel);
    }

    default Stream<NoticeModel> toStreamLibraryModel(Stream<NoticeEntity> entityStream) {
        return entityStream.map(this::toModel);
    }
}
