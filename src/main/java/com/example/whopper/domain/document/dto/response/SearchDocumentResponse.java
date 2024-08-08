package com.example.whopper.domain.document.dto.response;

import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.DocumentStatus;
import com.example.whopper.domain.student.domain.StudentInfo;

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
