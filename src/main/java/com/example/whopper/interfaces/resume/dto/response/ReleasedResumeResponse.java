package com.example.whopper.interfaces.resume.dto.response;

import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.domain.student.StudentInfo;

public record ReleasedResumeResponse(
        String resumeId,
        StudentInfo studentInfo
) {
    public static ReleasedResumeResponse of(ResumeModel resume) {
        return new ReleasedResumeResponse(resume.id(), StudentInfo.of(resume.writer()));
    }
}
