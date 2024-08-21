package com.example.whopper.domain.feedback.application.usecase;

import com.example.whopper.domain.feedback.dto.FeedbackResponse;
import com.example.whopper.global.utils.DataResponseInfo;

public interface FindFeedbackUseCase {
    DataResponseInfo<FeedbackResponse.StudentResponse> getCurrentStudentFeedbackList();
    DataResponseInfo<FeedbackResponse.TeacherResponse> getFeedbackListByDocumentId(String documentId);
    DataResponseInfo<FeedbackResponse.TeacherResponse> getFeedbacksWrittenByTeacher();
}
