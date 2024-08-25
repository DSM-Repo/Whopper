package com.example.whopper.application.resume.usecase;

import com.example.whopper.interfaces.resume.dto.response.CompletionElementLevelResponse;
import com.example.whopper.interfaces.resume.dto.response.ResumeResponse;
import com.example.whopper.interfaces.resume.dto.response.FullResumeResponse;
import com.example.whopper.interfaces.resume.dto.response.ReleasedResumeResponse;
import com.example.whopper.interfaces.resume.dto.response.SearchResumeResponse;
import com.example.whopper.common.http.response.DataResponseInfo;

public interface FindResumeUseCase {
    ResumeResponse getIntroduceRecentlySharedResumes();
    FullResumeResponse getCurrentStudentResume();
    FullResumeResponse getSubmittedResume(String resumeId);
    DataResponseInfo<SearchResumeResponse> searchResume(String name, Integer grade, Integer classNumber, String majorId, String status);
    CompletionElementLevelResponse getCurrentStudentResumeCompletionLevel();
    DataResponseInfo<ReleasedResumeResponse> getReleasedResumes();
    DataResponseInfo<FullResumeResponse> getReleasedResumesByGradeAndYear(int grade, int year);
}
