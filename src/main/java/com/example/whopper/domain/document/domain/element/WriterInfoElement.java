package com.example.whopper.domain.document.domain.element;

import lombok.Builder;

import java.util.Set;

@Builder
public record WriterInfoElement(
        String studentId,
        String name,
        String email,
        String profileImagePath,
        Integer grade,
        Integer classNumber,
        Integer number,
        String major,
        Set<String> skillSet,
        String url
) {
}
