package com.repo.whopper.domain.refreshtoken;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RefreshTokenEntityMapper {
    RefreshTokenModel toModel(RefreshTokenEntity entity);
    RefreshTokenEntity toEntity(RefreshTokenModel model);

    default Optional<RefreshTokenModel> toOptionalModel(Optional<RefreshTokenEntity> entity) {
        return entity.map(this::toModel);
    }
}
