package com.dsm.repo.internal.core.usecase.feedback;

import com.dsm.repo.external.web.rest.feedback.dto.response.FeedbackResponse;
import com.dsm.repo.external.web.rest.common.DataResponseInfo;

public interface FindFeedbackUseCase {
    DataResponseInfo<FeedbackResponse.StudentResponse> getCurrentStudentFeedbackList();
    DataResponseInfo<FeedbackResponse.TeacherResponse> getFeedbackListByResumeId(String resumeId);
    DataResponseInfo<FeedbackResponse.TeacherResponse> getFeedbacksWrittenByTeacher();
}
