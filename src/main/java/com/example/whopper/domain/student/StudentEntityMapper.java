package com.example.whopper.domain.student;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = StudentElementMapper.class)
public interface StudentEntityMapper {
    StudentEntity toEntity(StudentModel model);
    StudentModel toModel(StudentEntity entity);
}
