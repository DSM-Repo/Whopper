package com.example.whopper.application.resume.service;

import com.example.whopper.application.resume.usecase.FindResumeUseCase;
import com.example.whopper.domain.resume.ResumeRepository;
import com.example.whopper.domain.resume.detail.CompletionElementLevel;
import com.example.whopper.interfaces.resume.dto.response.ResumeResponse;
import com.example.whopper.interfaces.resume.dto.response.FullResumeResponse;
import com.example.whopper.interfaces.resume.dto.response.ReleasedResumeResponse;
import com.example.whopper.interfaces.resume.dto.response.SearchResumeResponse;
import com.example.whopper.common.exception.resume.ResumeNotFoundException;
import com.example.whopper.domain.feedback.FeedbackMongoRepository;
import com.example.whopper.domain.library.LibraryMongoRepository;
import com.example.whopper.domain.library.ShardLibrary;
import com.example.whopper.domain.student.StudentEntity;
import com.example.whopper.application.student.component.CurrentStudent;
import com.example.whopper.common.http.response.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class FindResumeService implements FindResumeUseCase {
    private final ResumeRepository resumeRepository;
    private final FeedbackMongoRepository feedbackMongoRepository;
    private final LibraryMongoRepository libraryMongoRepository;
    private final CurrentStudent currentStudent;

    @Override
    public ResumeResponse getIntroduceRecentlySharedResumes() {
        var currentStudentDocument = currentStudent.getDocument();
        var libraries = libraryMongoRepository.findTop3ByOrderByCreateAtDesc()
                .map(ShardLibrary::fromLibraryEntity)
                .toList();

        return ResumeResponse.of(
                currentStudentDocument,
                libraries
        );
    }

    @Override
    public FullResumeResponse getCurrentStudentResume() {
        var currentStudentDocument = currentStudent.getDocument();
        var student = currentStudentDocument.getStudent();
        var majorName = getMajorName(student);

        return FullResumeResponse.of(currentStudentDocument, majorName);
    }

    @Override
    public FullResumeResponse getSubmittedResume(String resumeId) {
        var document = resumeRepository.findById(resumeId)
                .orElseThrow(() -> ResumeNotFoundException.EXCEPTION);

        var student = document.getStudent();
        var majorName = getMajorName(student);

        return FullResumeResponse.of(document, majorName);
    }

    @Override
    public DataResponseInfo<SearchResumeResponse> searchResume(String name, Integer grade, Integer classNumber, String majorId, String status) {
        return DataResponseInfo.of(
                resumeRepository.searchDocuments(name, grade, classNumber, majorId, status)
                        .map(document -> SearchResumeResponse.of(
                                document,
                                feedbackMongoRepository.countByDocumentId(document.getId())
                        ))
                        .toList()
        );
    }

    @Override
    public CompletionElementLevel getCurrentStudentResumeCompletionLevel() {
        var currentStudentDocument = currentStudent.getDocument();

        return CompletionElementLevel.of(currentStudentDocument);
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
        var document = resumeRepository.getReleasedDocumentsByGenerationAndYear(generation, year);

        var response = document.map(doc -> FullResumeResponse.of(doc, doc.getStudent().getMajor().name()))
                .toList();

        return DataResponseInfo.of(response);
    }

    private String getMajorName(StudentEntity student) {
        return student.getMajor() == null ? "전공 미정" : student.getMajor().name();
    }
}
