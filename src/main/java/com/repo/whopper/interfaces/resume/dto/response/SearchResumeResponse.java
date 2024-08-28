package com.repo.whopper.interfaces.resume.dto.response;

import com.repo.whopper.domain.feedback.FeedbackModel;
import com.repo.whopper.domain.resume.ResumeModel;
import com.repo.whopper.domain.student.StudentInfo;
import com.repo.whopper.interfaces.resume.dto.ResumeElementDto;

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
