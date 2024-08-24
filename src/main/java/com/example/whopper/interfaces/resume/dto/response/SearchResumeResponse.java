package com.example.whopper.interfaces.resume.dto.response;

import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.domain.student.StudentInfo;
import com.example.whopper.interfaces.resume.dto.ResumeElementDto;

public record SearchResumeResponse(
        String documentId,
        StudentInfo studentInfo,
        ResumeElementDto.Status status,
        int numberOfFeedback
) {
    public static SearchResumeResponse of(ResumeModel resume, int numberOfFeedback) {
        return new SearchResumeResponse(resume.id(), StudentInfo.of(resume.writer()), resume.status(), numberOfFeedback);
    }
}
