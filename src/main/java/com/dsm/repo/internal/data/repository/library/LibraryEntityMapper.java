package com.dsm.repo.internal.data.repository.library;

import com.dsm.repo.internal.core.domain.model.library.LibraryModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;
import java.util.stream.Stream;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LibraryEntityMapper {
    LibraryModel toModel(LibraryEntity entity);
    LibraryEntity toEntity(LibraryModel model);

    default Optional<LibraryModel> toOptionalModel(Optional<LibraryEntity> optionalEntity) {
        return optionalEntity.map(this::toModel);
    }

    default Stream<LibraryModel> toStreamLibraryModel(Stream<LibraryEntity> entityStream) {
        return entityStream.map(this::toModel);
    }
}