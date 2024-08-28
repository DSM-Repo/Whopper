package com.repo.whopper.application.feedback.usecase;

import com.repo.whopper.interfaces.feedback.dto.response.FeedbackResponse;
import com.repo.whopper.common.http.dto.DataResponseInfo;

public interface FindFeedbackUseCase {
    DataResponseInfo<FeedbackResponse.StudentResponse> getCurrentStudentFeedbackList();
    DataResponseInfo<FeedbackResponse.TeacherResponse> getFeedbackListByResumeId(String resumeId);
    DataResponseInfo<FeedbackResponse.TeacherResponse> getFeedbacksWrittenByTeacher();
}
