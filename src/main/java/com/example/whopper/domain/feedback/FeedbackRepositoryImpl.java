package com.example.whopper.domain.feedback;

import com.example.whopper.interfaces.feedback.dto.FeedbackElementDto;
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

    public Optional<FeedbackModel> findById(String id) {
        return feedbackMongoRepository.findById(id)
                .map(feedbackEntityMapper::toModel);
    }

    public Stream<FeedbackModel> findAllByResumeIdAndStatus(String resumeId, FeedbackElementDto.Status status) {
        return feedbackMongoRepository.findAllByResumeIdAndStatus(resumeId, feedbackElementMapper.toStatusEntity(status))
                .map(feedbackEntityMapper::toModel);
    }

    public int countByResumeId(String resumeId) {
        return feedbackMongoRepository.countByResumeId(resumeId);
    }

    public void deleteById(String id) {
        feedbackMongoRepository.deleteById(id);
    }

    public void deleteAllByResumeId(String resumeId) {
        feedbackMongoRepository.deleteAllByResumeId(resumeId);
    }

    public Stream<FeedbackModel> findAllByWriterId(String writerId) {
        return feedbackMongoRepository.findAllByWriterId(writerId)
                .map(feedbackEntityMapper::toModel);
    }

    public Stream<FeedbackModel> findAllByResumeIdAndWriterId(String resumeId, String writerId) {
        return feedbackMongoRepository.findAllByResumeIdAndWriterId(resumeId, writerId)
                .map(feedbackEntityMapper::toModel);
    }

    public Stream<FeedbackModel> findAllByResumeIdInAndWriterId(List<String> resumeIds, String writerId) {
        return feedbackMongoRepository.findAllByResumeIdInAndWriterId(resumeIds, writerId)
                .map(feedbackEntityMapper::toModel);
    }
}