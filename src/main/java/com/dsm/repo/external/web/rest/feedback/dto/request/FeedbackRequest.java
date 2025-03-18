package com.dsm.repo.external.web.rest.feedback.dto.request;

public record FeedbackRequest(
        String comment,
        String type,
        String resumeId
) {}