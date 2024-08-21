package com.example.whopper.application.feedback.usecase;

import com.example.whopper.interfaces.feedback.dto.FeedbackResponse;
import com.example.whopper.global.utils.DataResponseInfo;

public interface FindFeedbackUseCase {
    DataResponseInfo<FeedbackResponse> getCurrentStudentFeedbackList();
    DataResponseInfo<FeedbackResponse> getFeedbackListByDocumentId(String documentId);
    DataResponseInfo<FeedbackResponse> getFeedbacksWrittenByTeacher();
}
