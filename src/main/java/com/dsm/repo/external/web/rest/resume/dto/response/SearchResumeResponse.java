package com.dsm.repo.external.web.rest.resume.dto.response;

import com.dsm.repo.internal.core.domain.model.feedback.FeedbackModel;
import com.dsm.repo.internal.core.domain.model.resume.ResumeModel;
import com.dsm.repo.internal.core.domain.model.student.StudentInfo;
import com.dsm.repo.external.web.rest.resume.dto.ResumeElementDto;

import java.util.List;

public record SearchResumeResponse(
        String resumeId,
        StudentInfo studentInfo,
        ResumeElementDto.Status status,
        List<Feedback> feedbackList
) {
    public static SearchResumeResponse of(ResumeModel resume, List<Feedback> feedbackList) {
        return new SearchResumeResponse(resume.id(), StudentInfo.of(resume.writer()), resume.status(), feedbackList);
    }

    public record Feedback(String id, String comment, String type, String status, boolean rejected) {
        public static SearchResumeResponse.Feedback fromFeedback(FeedbackModel feedback) {
            return new SearchResumeResponse.Feedback(feedback.id(), feedback.comment(), feedback.type().name(), feedback.status().name(), feedback.rejected());
        }
    }
}
