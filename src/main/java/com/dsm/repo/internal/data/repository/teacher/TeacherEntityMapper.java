package com.dsm.repo.internal.data.repository.teacher;

import com.dsm.repo.internal.core.domain.model.teacher.TeacherModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherEntityMapper {
    TeacherEntity toEntity(TeacherModel model);
    TeacherModel toModel(TeacherEntity entity);

    default Optional<TeacherModel> toOptionalModel(Optional<TeacherEntity> optionalEntity) {
        return optionalEntity.map(this::toModel);
    }
}
