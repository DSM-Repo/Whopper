package com.example.whopper.domain.document.domain.element;

import java.util.Set;

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
