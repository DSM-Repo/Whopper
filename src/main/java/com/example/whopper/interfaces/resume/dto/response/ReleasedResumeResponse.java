package com.example.whopper.interfaces.resume.dto.response;

import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.student.StudentInfo;

public record ReleasedResumeResponse(
        String documentId,
        StudentInfo studentInfo
) {
    public static ReleasedResumeResponse of(DocumentEntity document) {
        return new ReleasedResumeResponse(document.getId(), StudentInfo.of(document.getStudent()));
    }
}
