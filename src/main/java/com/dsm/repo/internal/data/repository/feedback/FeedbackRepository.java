package com.dsm.repo.internal.data.repository.feedback;

import com.dsm.repo.external.web.rest.feedback.dto.FeedbackElementDto;
import com.dsm.repo.internal.core.domain.model.feedback.FeedbackModel;

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