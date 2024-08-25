package com.example.whopper.interfaces.feedback.dto.response;

import com.example.whopper.domain.feedback.FeedbackModel;

public class FeedbackResponse{

    public record StudentResponse(
            String id,
            String comment,
            String type,
            String status,
            Boolean rejected,
            String teacherName
    ) {
        public static StudentResponse fromFeedback(FeedbackModel feedback) {
            return new StudentResponse(feedback.id(), feedback.comment(), feedback.type().name(), feedback.status().name(), feedback.rejected(), feedback.writer().name());
        }
    }

    public record TeacherResponse(
            String id,
            String type,
            String comment,
            String status,
            Boolean rejected
    ) {
        public static TeacherResponse fromFeedback(FeedbackModel feedback) {
            return new TeacherResponse(feedback.id(), feedback.comment(), feedback.type().name(), feedback.status().name(), feedback.rejected());
        }
    }
}