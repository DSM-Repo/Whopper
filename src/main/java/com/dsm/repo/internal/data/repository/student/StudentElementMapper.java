package com.dsm.repo.internal.data.repository.student;

import com.dsm.repo.external.web.rest.student.dto.StudentElementDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentElementMapper {
    StudentElementDto.ClassInfo toClassInfoDto(StudentEntity.ClassInfo classInfo);
    StudentEntity.ClassInfo toClassInfoEntity(StudentElementDto.ClassInfo classInfo);
}
