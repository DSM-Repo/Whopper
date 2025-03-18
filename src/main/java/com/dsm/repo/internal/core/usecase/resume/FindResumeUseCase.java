package com.dsm.repo.internal.core.usecase.resume;

import com.dsm.repo.external.web.rest.resume.dto.response.CompletionElementLevelResponse;
import com.dsm.repo.external.web.rest.resume.dto.response.ResumeResponse;
import com.dsm.repo.external.web.rest.resume.dto.response.FullResumeResponse;
import com.dsm.repo.external.web.rest.resume.dto.response.ReleasedResumeResponse;
import com.dsm.repo.external.web.rest.resume.dto.response.SearchResumeResponse;
import com.dsm.repo.external.web.rest.common.DataResponseInfo;

public interface FindResumeUseCase {
    ResumeResponse getIntroduceRecentlySharedResumes();
    FullResumeResponse getCurrentStudentResume();
    FullResumeResponse getSubmittedResume(String resumeId);
    DataResponseInfo<SearchResumeResponse> searchResume(String name, Integer grade, Integer classNumber, String majorId, String status);
    CompletionElementLevelResponse getCurrentStudentResumeCompletionLevel();
    DataResponseInfo<ReleasedResumeResponse> getReleasedResumes();
    DataResponseInfo<FullResumeResponse> getReleasedResumesByGradeAndYear(int grade, int year);
}
