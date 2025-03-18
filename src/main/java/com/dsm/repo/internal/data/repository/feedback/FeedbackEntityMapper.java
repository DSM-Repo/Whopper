package com.dsm.repo.internal.data.repository.feedback;

import com.dsm.repo.internal.core.domain.model.feedback.FeedbackModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;
import java.util.stream.Stream;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeedbackEntityMapper {
    FeedbackModel toModel(FeedbackEntity entity);
    FeedbackEntity toEntity(FeedbackModel model);

    default Optional<FeedbackModel> toOptionalModel(Optional<FeedbackEntity> optionalEntity) {
        return optionalEntity.map(this::toModel);
    }

    default Stream<FeedbackModel> toStreamLibraryModel(Stream<FeedbackEntity> entityStream) {
        return entityStream.map(this::toModel);
    }
}
