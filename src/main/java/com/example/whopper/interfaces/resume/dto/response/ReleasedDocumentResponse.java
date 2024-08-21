package com.example.whopper.interfaces.resume.dto.response;

import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.student.StudentInfo;

public record ReleasedDocumentResponse(
        String documentId,
        StudentInfo studentInfo
) {
    public static ReleasedDocumentResponse of(DocumentEntity document) {
        return new ReleasedDocumentResponse(document.getId(), StudentInfo.of(document.getStudent()));
    }
}
