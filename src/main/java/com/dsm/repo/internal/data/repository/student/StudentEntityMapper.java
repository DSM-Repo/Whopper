package com.dsm.repo.internal.data.repository.student;

import com.dsm.repo.internal.core.domain.model.student.StudentModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = StudentElementMapper.class)
public interface StudentEntityMapper {
    StudentEntity toEntity(StudentModel model);
    StudentModel toModel(StudentEntity entity);

    default Optional<StudentModel> toOptionalModel(Optional<StudentEntity> optionalEntity) {
        return optionalEntity.map(this::toModel);
    }
}
