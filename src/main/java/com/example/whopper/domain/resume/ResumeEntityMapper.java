package com.example.whopper.domain.resume;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ResumeElementMapper.class})
public interface ResumeEntityMapper {
    ResumeModel toModel(ResumeEntity entity);
    ResumeEntity toEntity(ResumeModel model);

    default Optional<ResumeModel> toOptionalModel(Optional<ResumeEntity> optionalEntity) {
        return optionalEntity.map(this::toModel);
    }

    default Optional<ResumeEntity> toOptionalEntity(Optional<ResumeModel> model) {
        return model.map(this::toEntity);
    }

}
