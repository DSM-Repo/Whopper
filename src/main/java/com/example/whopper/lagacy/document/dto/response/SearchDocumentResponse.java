package com.example.whopper.lagacy.document.dto.response;

import com.example.whopper.lagacy.document.domain.DocumentEntity;
import com.example.whopper.lagacy.document.domain.element.DocumentStatus;
import com.example.whopper.lagacy.student.domain.StudentInfo;

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
