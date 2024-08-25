package com.example.whopper.interfaces.feedback.dto;

import com.example.whopper.domain.feedback.FeedbackEntity;

public class FeedbackResponse{

    public record StudentResponse(
            String id,
            String teacherName,
            String type,
            String comment,
            String status,
            Boolean rejected
    ) {
        public static StudentResponse fromFeedback(FeedbackEntity feedback) {
            return new StudentResponse(feedback.getId(), feedback.getTeacher().getName(), feedback.getType().name(), feedback.getComment(), String.valueOf(feedback.getStatus()), feedback.getRejected());
        }
    }

    public record TeacherResponse(
            String id,
            String type,
            String comment,
            String status,
            Boolean rejected
    ) {
        public static TeacherResponse fromFeedback(FeedbackEntity feedback) {
            return new TeacherResponse(feedback.getId(), feedback.getType().name(), feedback.getComment(), String.valueOf(feedback.getStatus()), feedback.getRejected());
        }
    }
}
