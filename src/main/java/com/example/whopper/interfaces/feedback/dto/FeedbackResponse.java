package com.example.whopper.interfaces.feedback.dto;

import com.example.whopper.domain.resume.element.type.DocumentElementType;
import com.example.whopper.domain.feedback.FeedbackEntity;

public class FeedbackResponse{

    public record StudentResponse(
            String id,
            String teacherName,
            DocumentElementType type,
            String comment,
            String status,
            Boolean rejected
    ) {
        public static StudentResponse fromFeedback(FeedbackEntity feedback) {
            return new StudentResponse(feedback.getId(), feedback.getTeacher().getName(), feedback.getType(), feedback.getComment(), String.valueOf(feedback.getStatus()), feedback.getRejected());
        }
    }

    public record TeacherResponse(
            String id,
            DocumentElementType type,
            String comment,
            String status,
            Boolean rejected
    ) {
        public static TeacherResponse fromFeedback(FeedbackEntity feedback) {
            return new TeacherResponse(feedback.getId(), feedback.getType(), feedback.getComment(), String.valueOf(feedback.getStatus()), feedback.getRejected());
        }
    }
}
