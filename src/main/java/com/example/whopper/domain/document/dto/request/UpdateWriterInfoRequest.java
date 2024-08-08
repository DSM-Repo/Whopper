package com.example.whopper.domain.document.dto.request;

import com.example.whopper.domain.student.domain.ClassInfo;

import java.util.Set;

public record UpdateWriterInfoRequest(
        String majorId,
        String email,
        String url,
        Set<String> skillSet
) {
}
