package com.repo.whopper.application.resume.service;

import com.repo.whopper.application.resume.usecase.FindResumeUseCase;
import com.repo.whopper.application.teacher.component.CurrentTeacher;
import com.repo.whopper.domain.feedback.FeedbackModel;
import com.repo.whopper.domain.feedback.FeedbackRepository;
import com.repo.whopper.domain.library.LibraryRepository;
import com.repo.whopper.domain.resume.ResumeModel;
import com.repo.whopper.domain.resume.ResumeRepository;
import com.repo.whopper.interfaces.resume.dto.response.CompletionElementLevelResponse;
import com.repo.whopper.interfaces.resume.dto.response.*;
import com.repo.whopper.common.exception.resume.ResumeNotFoundException;
import com.repo.whopper.application.student.component.CurrentStudent;
import com.repo.whopper.common.http.response.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class FindResumeService implements FindResumeUseCase {
    private final ResumeRepository resumeRepository;
    private final FeedbackRepository feedbackRepository;
    private final LibraryRepository libraryRepository;
    private final CurrentStudent currentStudent;
    private final CurrentTeacher currentTeacher;

    @Override
    public ResumeResponse getIntroduceRecentlySharedResumes() {
        final var currentStudentResume = currentStudent.getResume();
        final var libraries = libraryRepository.findTop3ByOrderByCreateAtDesc()
                .map(ResumeResponse.ShardLibrary::toShardLibrary)
                .toList();

        return ResumeResponse.of(
                currentStudentResume,
                libraries
        );
    }

    @Override
    public FullResumeResponse getCurrentStudentResume() {
        final var currentStudentResume = currentStudent.getResume();

        return FullResumeResponse.of(currentStudentResume);
    }

    @Override
    public FullResumeResponse getSubmittedResume(String resumeId) {
        final var resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> ResumeNotFoundException.EXCEPTION);

        return FullResumeResponse.of(resume);
    }

    @Override
    public DataResponseInfo<SearchResumeResponse> searchResume(String name, Integer grade, Integer classNumber, String majorId, String status) {
        final var WriterId = currentTeacher.getTeacher().id();

        final var resumes = resumeRepository.searchResumes(name, grade, classNumber, majorId, status).toList();
        final var resumeIds = resumes.stream().map(ResumeModel::id).toList();

        Map<String, List<FeedbackModel>> feedbackMap = feedbackRepository.findAllByResumeIdInAndWriterId(resumeIds, WriterId)
                .collect(Collectors.groupingBy(FeedbackModel::resumeId));

        final var responses = resumes.stream()
                .map(resume -> SearchResumeResponse.of(
                        resume,
                        Optional.ofNullable(feedbackMap.get(resume.id()))
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(SearchResumeResponse.Feedback::fromFeedback)
                                .toList()
                ))
                .toList();

        return DataResponseInfo.of(responses);
    }

    @Override
    public CompletionElementLevelResponse getCurrentStudentResumeCompletionLevel() {
        final var currentStudentResume = currentStudent.getResume();

        return CompletionElementLevelResponse.of(currentStudentResume);
    }

    @Override
    public DataResponseInfo<ReleasedResumeResponse> getReleasedResumes() {
        return DataResponseInfo.of(
                resumeRepository.getReleasedResumes()
                        .map(ReleasedResumeResponse::of)
                        .toList()
        );
    }

    @Override
    public DataResponseInfo<FullResumeResponse> getReleasedResumesByGradeAndYear(int grade, int year) {
        final var generation = (year - 2013) - grade;
        final var resume = resumeRepository.getReleasedResumesByGenerationAndYear(generation, year);

        final var response = resume.map(FullResumeResponse::of)
                .toList();

        return DataResponseInfo.of(response);
    }
}