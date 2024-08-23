package com.example.whopper.domain.major;

import java.util.List;
import java.util.Optional;

public class MajorEntityMapper {

    public static MajorModel toModel(MajorEntity entity) {
        return new MajorModel(
                entity.getId(),
                entity.getName()
        );
    }

    public static Optional<MajorModel> toOptionalModel(Optional<MajorEntity> entity) {
        return entity.map(MajorEntityMapper::toModel);
    }

    public static MajorEntity toEntity(MajorModel model) {
        return new MajorEntity(
                model.id(),
                model.name()
        );
    }

    public static List<MajorModel> toModelList(List<MajorEntity> entities) {
        return entities.stream()
                .map(MajorEntityMapper::toModel)
                .toList();
    }

    public static List<MajorEntity> toEntityList(List<MajorModel> models) {
        return models.stream()
                .map(MajorEntityMapper::toEntity)
                .toList();
    }
}
