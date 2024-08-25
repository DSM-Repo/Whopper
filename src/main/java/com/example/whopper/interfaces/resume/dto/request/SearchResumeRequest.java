package com.example.whopper.interfaces.resume.dto.request;

public record SearchResumeRequest(
        String name,
        Integer grade,
        Integer classNumber,
        String majorId,
        String status
) {
}
