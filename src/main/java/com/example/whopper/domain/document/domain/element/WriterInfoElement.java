package com.example.whopper.domain.document.domain.element;

import lombok.Builder;

import java.util.Set;

@Builder
public record WriterInfoElement(
        String email,
        String profileImagePath,
        String major,
        Set<String> skillSet,
        String url
) {
}
