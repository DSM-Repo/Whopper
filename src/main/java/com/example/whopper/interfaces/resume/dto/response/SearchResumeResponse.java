package com.example.whopper.interfaces.resume.dto.response;

import com.example.whopper.domain.feedback.FeedbackModel;
import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.domain.student.StudentInfo;
import com.example.whopper.interfaces.resume.dto.ResumeElementDto;

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
