package com.example.whopper.domain.library;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LibraryEntityMapper {
    LibraryModel toModel(LibraryEntity entity);
    LibraryEntity toEntity(LibraryModel model);
}
