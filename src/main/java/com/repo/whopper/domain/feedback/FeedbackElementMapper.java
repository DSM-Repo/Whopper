package com.repo.whopper.domain.feedback;

import com.repo.whopper.interfaces.feedback.dto.FeedbackElementDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeedbackElementMapper {
    FeedbackEntity.Status toStatusEntity(FeedbackElementDto.Status status);
}
