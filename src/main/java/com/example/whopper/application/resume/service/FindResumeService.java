package com.example.whopper.application.resume.service;

import com.example.whopper.application.resume.usecase.FindResumeUseCase;
import com.example.whopper.application.teacher.component.TeacherComponent;
import com.example.whopper.domain.feedback.FeedbackModel;
import com.example.whopper.domain.feedback.FeedbackRepository;
import com.example.whopper.domain.library.LibraryRepository;
import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.domain.resume.ResumeRepository;
import com.example.whopper.interfaces.resume.dto.response.CompletionElementLevelResponse;
import com.example.whopper.interfaces.resume.dto.response.*;
import com.example.whopper.common.exception.resume.ResumeNotFoundException;
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
    private final FeedbackRepository feedbackRepository;
    private final LibraryRepository libraryRepository;
    private final CurrentStudent currentStudent;
    private final TeacherComponent teacherComponent;

    @Override
    public ResumeResponse getIntroduceRecentlySharedResumes() {
        var currentStudentResume = currentStudent.getResume();
        var libraries = libraryRepository.findTop3ByOrderByCreateAtDesc()
                .map(ResumeResponse.ShardLibrary::toShardLibrary)
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
        var WriterId = teacherComponent.currentTeacher().getId();

        var resumes = resumeRepository.searchResumes(name, grade, classNumber, majorId, status).toList();
        var resumeIds = resumes.stream().map(ResumeModel::id).toList();

        Map<String, List<FeedbackModel>> feedbackMap = feedbackRepository.findAllByResumeIdInAndWriterId(resumeIds, WriterId)
                .collect(Collectors.groupingBy(FeedbackModel::resumeId));

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
    public CompletionElementLevelResponse getCurrentStudentResumeCompletionLevel() {
        var currentStudentResume = currentStudent.getResume();

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
        var generation = (year - 2013) - grade;
        var resume = resumeRepository.getReleasedResumesByGenerationAndYear(generation, year);

        var response = resume.map(FullResumeResponse::of)
                .toList();

        return DataResponseInfo.of(response);
    }
}
