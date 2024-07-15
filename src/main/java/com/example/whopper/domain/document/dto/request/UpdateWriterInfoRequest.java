package com.example.whopper.domain.document.dto.request;

import com.example.whopper.domain.student.domain.ClassInfo;

import java.util.Set;

public record UpdateWriterInfoRequest(
        String major,
        String email,
        ClassInfo classInfo,
        String url,
        Set<String> skillSet
) {
}
