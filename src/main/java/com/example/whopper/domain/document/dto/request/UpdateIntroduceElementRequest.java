package com.example.whopper.domain.document.dto.request;

public record UpdateIntroduceElementRequest(
        String elementId,
        String heading,
        String introduce
) {
}
