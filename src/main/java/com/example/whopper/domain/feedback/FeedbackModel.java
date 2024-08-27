package com.example.whopper.domain.feedback;

import com.example.whopper.interfaces.feedback.dto.FeedbackElementDto;

@Builder
public record FeedbackModel(
        String id,
        String comment,
        FeedbackElementDto.Type type,
        String documentId,
        FeedbackElementDto.Status status,
        Boolean rejected,
        FeedbackElementDto.Writer writer

) {

    public FeedbackModel update(String comment) {
        return new FeedbackModel(id, comment, type, documentId, status, rejected, writer);
    }

    public FeedbackModel confirm() {
        return new FeedbackModel(id, comment, type, documentId, FeedbackElementDto.Status.CONFIRMED, rejected, writer);
    }

    public FeedbackModel reject() {
        return new FeedbackModel(id, comment, type, documentId, status, true, writer);
    }
}

