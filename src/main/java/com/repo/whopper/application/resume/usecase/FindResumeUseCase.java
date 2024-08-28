package com.repo.whopper.application.resume.usecase;

import com.repo.whopper.interfaces.resume.dto.response.CompletionElementLevelResponse;
import com.repo.whopper.interfaces.resume.dto.response.ResumeResponse;
import com.repo.whopper.interfaces.resume.dto.response.FullResumeResponse;
import com.repo.whopper.interfaces.resume.dto.response.ReleasedResumeResponse;
import com.repo.whopper.interfaces.resume.dto.response.SearchResumeResponse;
import com.repo.whopper.common.http.response.DataResponseInfo;

public interface FindResumeUseCase {
    ResumeResponse getIntroduceRecentlySharedResumes();
    FullResumeResponse getCurrentStudentResume();
    FullResumeResponse getSubmittedResume(String resumeId);
    DataResponseInfo<SearchResumeResponse> searchResume(String name, Integer grade, Integer classNumber, String majorId, String status);
    CompletionElementLevelResponse getCurrentStudentResumeCompletionLevel();
    DataResponseInfo<ReleasedResumeResponse> getReleasedResumes();
    DataResponseInfo<FullResumeResponse> getReleasedResumesByGradeAndYear(int grade, int year);
}
