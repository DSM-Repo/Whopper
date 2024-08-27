package com.example.whopper.domain.feedback;

import com.example.whopper.interfaces.feedback.dto.FeedbackElementDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeedbackElementMapper {
    FeedbackEntity.Status toStatusEntity(FeedbackElementDto.Status status);
}
