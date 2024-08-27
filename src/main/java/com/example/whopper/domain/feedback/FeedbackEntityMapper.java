package com.example.whopper.domain.feedback;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeedbackEntityMapper {
    FeedbackModel toModel(FeedbackEntity entity);
    FeedbackEntity toEntity(FeedbackModel model);
}
