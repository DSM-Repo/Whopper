package com.dsm.repo.internal.data.repository.major;

import com.dsm.repo.internal.core.domain.model.major.MajorModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MajorEntityMapper {
    MajorModel toModel(MajorEntity entity);
    MajorEntity toEntity(MajorModel model);

    default Optional<MajorModel> toOptionalModel(Optional<MajorEntity> optionalEntity) {
        return optionalEntity.map(this::toModel);
    }

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
