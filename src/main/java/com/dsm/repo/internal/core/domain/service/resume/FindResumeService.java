package com.dsm.repo.internal.core.domain.service.resume;

import com.dsm.repo.internal.core.usecase.resume.FindResumeUseCase;
import com.dsm.repo.internal.core.domain.event.teacher.CurrentTeacher;
import com.dsm.repo.internal.core.domain.model.feedback.FeedbackModel;
import com.dsm.repo.internal.data.repository.feedback.FeedbackRepository;
import com.dsm.repo.internal.data.repository.library.LibraryRepository;
import com.dsm.repo.internal.core.domain.model.resume.ResumeModel;
import com.dsm.repo.internal.data.repository.resume.ResumeRepository;
import com.dsm.repo.external.web.rest.resume.dto.response.CompletionElementLevelResponse;
import com.dsm.repo.external.web.rest.resume.dto.response.*;
import com.dsm.repo.external.exception.domain.resume.ResumeNotFoundException;
import com.dsm.repo.internal.core.domain.component.student.CurrentStudent;
import com.dsm.repo.external.web.rest.common.DataResponseInfo;
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