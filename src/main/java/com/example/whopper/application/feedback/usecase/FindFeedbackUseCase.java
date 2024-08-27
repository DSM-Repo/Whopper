package com.example.whopper.application.feedback.usecase;

import com.example.whopper.interfaces.feedback.dto.response.FeedbackResponse;
import com.example.whopper.common.http.response.DataResponseInfo;

public interface FindFeedbackUseCase {
    DataResponseInfo<FeedbackResponse.StudentResponse> getCurrentStudentFeedbackList();
    DataResponseInfo<FeedbackResponse.TeacherResponse> getFeedbackListByResumeId(String resumeId);
    DataResponseInfo<FeedbackResponse.TeacherResponse> getFeedbacksWrittenByTeacher();
}
