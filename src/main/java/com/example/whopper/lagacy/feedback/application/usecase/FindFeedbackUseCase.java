package com.example.whopper.lagacy.feedback.application.usecase;

import com.example.whopper.lagacy.feedback.dto.FeedbackResponse;
import com.example.whopper.global.utils.DataResponseInfo;

public interface FindFeedbackUseCase {
    DataResponseInfo<FeedbackResponse> getCurrentStudentFeedbackList();
    DataResponseInfo<FeedbackResponse> getFeedbackListByDocumentId(String documentId);
    DataResponseInfo<FeedbackResponse> getFeedbacksWrittenByTeacher();
}
