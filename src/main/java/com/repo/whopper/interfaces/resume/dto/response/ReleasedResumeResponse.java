package com.repo.whopper.interfaces.resume.dto.response;

import com.repo.whopper.domain.resume.ResumeModel;
import com.repo.whopper.domain.student.StudentInfo;

public record ReleasedResumeResponse(
        String resumeId,
        StudentInfo studentInfo
) {
    public static ReleasedResumeResponse of(ResumeModel resume) {
        return new ReleasedResumeResponse(resume.id(), StudentInfo.of(resume.writer()));
    }
}
