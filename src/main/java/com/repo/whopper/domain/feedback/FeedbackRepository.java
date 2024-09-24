package com.repo.whopper.domain.feedback;

import com.repo.whopper.interfaces.feedback.dto.FeedbackElementDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface FeedbackRepository {
    FeedbackModel save(FeedbackModel feedback);
    Optional<FeedbackModel> findById(String feedbackId);
    void deleteById(String feedbackId);
    void deleteAllByResumeId(String resumeId);
    Stream<FeedbackModel> findAllByResumeIdAndStatus(String resumeId, FeedbackElementDto.Status status);
    Stream<FeedbackModel> findAllByWriterId(String WriterId);
    Stream<FeedbackModel> findAllByResumeIdAndWriterId(String resumeId, String WriterId);
    Stream<FeedbackModel> findAllByResumeIdInAndWriterId(List<String> resumeIds, String WriterId);
}