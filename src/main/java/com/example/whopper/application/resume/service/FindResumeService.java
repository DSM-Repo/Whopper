package com.example.whopper.application.resume.service;

import com.example.whopper.application.resume.usecase.FindResumeUseCase;
import com.example.whopper.application.teacher.component.TeacherComponent;
import com.example.whopper.domain.feedback.FeedbackEntity;
import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.domain.resume.ResumeRepository;
import com.example.whopper.domain.resume.detail.CompletionElementLevel;
import com.example.whopper.interfaces.resume.dto.response.*;
import com.example.whopper.common.exception.resume.ResumeNotFoundException;
import com.example.whopper.domain.feedback.FeedbackMongoRepository;
import com.example.whopper.domain.library.LibraryMongoRepository;
import com.example.whopper.domain.library.ShardLibrary;
import com.example.whopper.application.student.component.CurrentStudent;
import com.example.whopper.common.http.response.DataResponseInfo;
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
    private final FeedbackMongoRepository feedbackMongoRepository;
    private final LibraryMongoRepository libraryMongoRepository;
    private final CurrentStudent currentStudent;
    private final TeacherComponent teacherComponent;

    @Override
    public ResumeResponse getIntroduceRecentlySharedResumes() {
        var currentStudentResume = currentStudent.getResume();
        var libraries = libraryMongoRepository.findTop3ByOrderByCreateAtDesc()
                .map(ShardLibrary::fromLibraryEntity)
                .toList();

        return ResumeResponse.of(
                currentStudentResume,
                libraries
        );
    }

    @Override
    public FullResumeResponse getCurrentStudentResume() {
        var currentStudentResume = currentStudent.getResume();

        return FullResumeResponse.of(currentStudentResume);
    }

    @Override
    public FullResumeResponse getSubmittedResume(String resumeId) {
        var resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> ResumeNotFoundException.EXCEPTION);

        return FullResumeResponse.of(resume);
    }

    @Override
    public DataResponseInfo<SearchResumeResponse> searchResume(String name, Integer grade, Integer classNumber, String majorId, String status) {
        var teacherId = teacherComponent.currentTeacher().getId();

        var resumes = resumeRepository.searchDocuments(name, grade, classNumber, majorId, status).toList();
        var resumeIds = resumes.stream().map(ResumeModel::id).toList();

        Map<String, List<FeedbackEntity>> feedbackMap = feedbackMongoRepository.findAllByDocumentIdInAndTeacherId(resumeIds, teacherId)
                .stream()
                .collect(Collectors.groupingBy(FeedbackEntity::getDocumentId));

        var responses = resumes.stream()
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
    public CompletionElementLevel getCurrentStudentResumeCompletionLevel() {
        var currentStudentResume = currentStudent.getResume();

        return CompletionElementLevel.of(currentStudentResume);
    }

    @Override
    public DataResponseInfo<ReleasedResumeResponse> getReleasedResumes() {
        return DataResponseInfo.of(
                resumeRepository.getReleasedDocuments()
                        .map(ReleasedResumeResponse::of)
                        .toList()
        );
    }

    @Override
    public DataResponseInfo<FullResumeResponse> getReleasedResumesByGradeAndYear(int grade, int year) {
        var generation = (year - 2013) - grade;
        var resume = resumeRepository.getReleasedDocumentsByGenerationAndYear(generation, year);

        var response = resume.map(FullResumeResponse::of)
                .toList();

        return DataResponseInfo.of(response);
    }
}