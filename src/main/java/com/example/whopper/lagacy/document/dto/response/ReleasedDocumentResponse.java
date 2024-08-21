package com.example.whopper.lagacy.document.dto.response;

import com.example.whopper.lagacy.document.domain.DocumentEntity;
import com.example.whopper.lagacy.student.domain.StudentInfo;

public record ReleasedDocumentResponse(
        String documentId,
        StudentInfo studentInfo
) {
    public static ReleasedDocumentResponse of(DocumentEntity document) {
        return new ReleasedDocumentResponse(document.getId(), StudentInfo.of(document.getStudent()));
    }
}
