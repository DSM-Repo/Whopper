package com.example.whopper.interfaces.resume.dto.response;

import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.resume.element.DocumentStatus;
import com.example.whopper.domain.student.StudentInfo;

public record SearchDocumentResponse(
        String documentId,
        StudentInfo studentInfo,
        DocumentStatus status,
        int numberOfFeedback
) {
    public static SearchDocumentResponse of(DocumentEntity document, int numberOfFeedback) {
        return new SearchDocumentResponse(document.getId(), StudentInfo.of(document.getStudent()), document.getStatus(), numberOfFeedback);
    }
}
