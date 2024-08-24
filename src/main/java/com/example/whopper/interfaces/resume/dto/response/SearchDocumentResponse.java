package com.example.whopper.interfaces.resume.dto.response;

import com.example.whopper.domain.feedback.FeedbackEntity;
import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.resume.element.DocumentStatus;
import com.example.whopper.domain.student.StudentInfo;

import java.util.List;

public record SearchDocumentResponse(
        String documentId,
        StudentInfo studentInfo,
        DocumentStatus status,
        List<Feedback> feedbackList
) {
    public record Feedback(String id, String comment, String type, String status, boolean rejected) {
        public static Feedback fromFeedback(FeedbackEntity feedback) {
            return new Feedback(feedback.getId(), feedback.getComment(), feedback.getType().name(), feedback.getStatus().name(), feedback.getRejected());
        }
    }
    public static SearchDocumentResponse of(DocumentEntity document, List<Feedback> feedbackList) {
        return new SearchDocumentResponse(document.getId(), StudentInfo.of(document.getStudent()), document.getStatus(), feedbackList);
    }
}
