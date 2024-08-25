package com.example.whopper.domain.feedback;

import com.example.whopper.domain.resume.element.type.DocumentElementType;

public record FeedbackModel(
        String id,
        String comment,
        DocumentElementType type,
        String documentId,
        FeedbackEntity.Status status,
        Boolean rejected,
        String Writer
) {
    record Writer(
            String id,
            String name
    ) {}

    public FeedbackModel update(String comment) {
        return new FeedbackModel(id, comment, type, documentId, status, rejected, teacherId);
    }

    public void confirmed() {
        status = FeedbackEntity.Status.CONFIRMED;
    }

    public void rejected() {
        rejected = true;
    }
}

