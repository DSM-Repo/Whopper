package com.example.whopper.domain.feedback.dto;

import com.example.whopper.domain.document.domain.element.type.DocumentElementType;
import com.example.whopper.domain.feedback.domain.FeedbackEntity;

public class FeedbackResponse{

    public record StudentResponse(
            String id,
            String teacherName,
            DocumentElementType type,
            String comment,
            String status
    ) {
        public static StudentResponse fromFeedback(FeedbackEntity feedback) {
            return new StudentResponse(feedback.getId(), feedback.getTeacher().getName(), feedback.getType(), feedback.getComment(), String.valueOf(feedback.getStatus()));
        }
    }

    public record TeacherResponse(
            String id,
            DocumentElementType type,
            String comment,
            String status
    ) {
        public static TeacherResponse fromFeedback(FeedbackEntity feedback) {
            return new TeacherResponse(feedback.getId(), feedback.getType(), feedback.getComment(), String.valueOf(feedback.getStatus()));
        }
    }
}
