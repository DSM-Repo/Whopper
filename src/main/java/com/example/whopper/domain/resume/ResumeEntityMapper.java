package com.example.whopper.domain.resume;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ResumeElementMapper.class})
public interface ResumeEntityMapper {
    ResumeModel toModel(ResumeEntity entity);
    ResumeEntity toEntity(ResumeModel model);

    default Optional<ResumeModel> toOptionalModel(Optional<ResumeEntity> entity) {
        return entity.map(this::toModel);
    }

    default Optional<ResumeEntity> toOptionalEntity(Optional<ResumeModel> model) {
        return model.map(this::toEntity);
    }

}
