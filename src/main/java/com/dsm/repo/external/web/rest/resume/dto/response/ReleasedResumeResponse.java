package com.dsm.repo.external.web.rest.resume.dto.response;

import com.dsm.repo.internal.core.domain.model.resume.ResumeModel;
import com.dsm.repo.internal.core.domain.model.student.StudentInfo;

public record ReleasedResumeResponse(
        String resumeId,
        StudentInfo studentInfo
) {
    public static ReleasedResumeResponse of(ResumeModel resume) {
        return new ReleasedResumeResponse(resume.id(), StudentInfo.of(resume.writer()));
    }
}
