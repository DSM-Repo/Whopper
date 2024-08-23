package com.example.whopper.application.feedback.usecase;

import com.example.whopper.interfaces.feedback.dto.FeedbackResponse;
import com.example.whopper.common.http.response.DataResponseInfo;

public interface FindFeedbackUseCase {
    DataResponseInfo<FeedbackResponse.StudentResponse> getCurrentStudentFeedbackList();
    DataResponseInfo<FeedbackResponse.TeacherResponse> getFeedbackListByDocumentId(String documentId);
    DataResponseInfo<FeedbackResponse.TeacherResponse> getFeedbacksWrittenByTeacher();
}
