package com.example.whopper.domain.student;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = StudentElementMapper.class)
public interface StudentEntityMapper {
    StudentEntity toEntity(StudentModel model);
    StudentModel toModel(StudentEntity entity);

    default Optional<StudentModel> toOptionalModel(Optional<StudentEntity> entity) {
        return entity.map(this::toModel);
    }
}
