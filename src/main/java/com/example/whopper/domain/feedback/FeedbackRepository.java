package com.example.whopper.domain.feedback;

import com.example.whopper.interfaces.feedback.dto.FeedbackElementDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface FeedbackRepository {
    FeedbackModel save(FeedbackModel feedback);
    Optional<FeedbackModel> findById(String id);
    Stream<FeedbackModel> findAllByResumeIdAndStatus(String resumeId, FeedbackElementDto.Status status);
    int countByResumeId(String resumeId);
    void deleteById(String id);
    void deleteAllByResumeId(String resumeId);
    Stream<FeedbackModel> findAllByWriterId(String WriterId);
    Stream<FeedbackModel> findAllByResumeIdAndWriterId(String resumeId, String WriterId);
    Stream<FeedbackModel> findAllByResumeIdInAndWriterId(List<String> resumeIds, String WriterId);
}
