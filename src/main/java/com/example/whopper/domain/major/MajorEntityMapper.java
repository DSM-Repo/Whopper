package com.example.whopper.domain.major;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MajorEntityMapper {

    MajorModel toModel(MajorEntity entity);

    default Optional<MajorModel> toOptionalModel(Optional<MajorEntity> entity) {
        return entity.map(this::toModel);
    }

    MajorEntity toEntity(MajorModel model);

    default List<MajorModel> toModelList(List<MajorEntity> entities) {
        return entities.stream()
                .map(this::toModel)
                .toList();
    }

    default List<MajorEntity> toEntityList(List<MajorModel> models) {
        return models.stream()
                .map(this::toEntity)
                .toList();
    }
}
