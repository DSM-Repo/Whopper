package com.example.whopper.domain.feedback;

import com.example.whopper.interfaces.feedback.dto.FeedbackElementDto;

public record FeedbackModel(
        String id,
        String comment,
        FeedbackElementDto.Type type,
        String resumeId,
        FeedbackElementDto.Status status,
        Boolean rejected,
        FeedbackElementDto.Writer writer
) {

    public FeedbackModel update(String comment) {
        return new FeedbackModel(id, comment, type, resumeId, status, rejected, writer);
    }

    public FeedbackModel confirm() {
        return new FeedbackModel(id, comment, type, resumeId, FeedbackElementDto.Status.CONFIRMED, rejected, writer);
    }

    public FeedbackModel reject() {
        return new FeedbackModel(id, comment, type, resumeId, status, true, writer);
    }
}

