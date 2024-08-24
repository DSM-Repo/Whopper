package com.example.whopper.interfaces.resume.dto.response;

import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.resume.element.DocumentStatus;
import com.example.whopper.domain.student.StudentInfo;

public record SearchResumeResponse(
        String documentId,
        StudentInfo studentInfo,
        DocumentStatus status,
        int numberOfFeedback
) {
    public static SearchResumeResponse of(DocumentEntity document, int numberOfFeedback) {
        return new SearchResumeResponse(document.getId(), StudentInfo.of(document.getStudent()), document.getStatus(), numberOfFeedback);
    }
}
