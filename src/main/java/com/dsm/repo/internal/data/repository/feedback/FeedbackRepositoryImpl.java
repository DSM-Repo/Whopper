package com.dsm.repo.internal.data.repository.feedback;

import com.dsm.repo.external.web.rest.feedback.dto.FeedbackElementDto;
import com.dsm.repo.internal.core.domain.model.feedback.FeedbackModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
class FeedbackRepositoryImpl implements FeedbackRepository {
    private final FeedbackMongoRepository feedbackMongoRepository;
    private final FeedbackEntityMapper feedbackEntityMapper;
    private final FeedbackElementMapper feedbackElementMapper;

    @Override
    public FeedbackModel save(FeedbackModel feedback) {
        final var entity =  feedbackEntityMapper.toEntity(feedback);

        return feedbackEntityMapper.toModel(feedbackMongoRepository.save(entity));
    }

    public Optional<FeedbackModel> findById(String feedbackId) {
        final var result = feedbackMongoRepository.findById(feedbackId);

        return feedbackEntityMapper.toOptionalModel(result);
    }

    public Stream<FeedbackModel> findAllByResumeIdAndStatus(String resumeId, FeedbackElementDto.Status status) {
        final var result = feedbackMongoRepository.findAllByResumeIdAndStatus(resumeId, feedbackElementMapper.toStatusEntity(status));

        return feedbackEntityMapper.toStreamLibraryModel(result);
    }

    public void deleteById(String feedbackId) {
        feedbackMongoRepository.deleteById(feedbackId);
    }

    public void deleteAllByResumeId(String resumeId) {
        feedbackMongoRepository.deleteAllByResumeId(resumeId);
    }

    public Stream<FeedbackModel> findAllByWriterId(String writerId) {
        final var result = feedbackMongoRepository.findAllByWriterId(writerId);

        return feedbackEntityMapper.toStreamLibraryModel(result);
    }

    public Stream<FeedbackModel> findAllByResumeIdAndWriterId(String resumeId, String writerId) {
        final var result = feedbackMongoRepository.findAllByResumeIdAndWriterId(resumeId, writerId);

        return feedbackEntityMapper.toStreamLibraryModel(result);
    }

    public Stream<FeedbackModel> findAllByResumeIdInAndWriterId(List<String> resumeIds, String writerId) {
        final var result = feedbackMongoRepository.findAllByResumeIdInAndWriterId(resumeIds, writerId);

        return feedbackEntityMapper.toStreamLibraryModel(result);
    }
}