package com.example.whopper.application.resume.service;

import com.example.whopper.application.resume.usecase.FindDocumentUseCase;
import com.example.whopper.application.teacher.component.TeacherComponent;
import com.example.whopper.domain.feedback.FeedbackEntity;
import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.resume.DocumentRepository;
import com.example.whopper.domain.resume.detail.CompletionElementLevel;
import com.example.whopper.interfaces.resume.dto.response.DocumentResponse;
import com.example.whopper.interfaces.resume.dto.response.FullDocumentResponse;
import com.example.whopper.interfaces.resume.dto.response.ReleasedDocumentResponse;
import com.example.whopper.interfaces.resume.dto.response.SearchDocumentResponse;
import com.example.whopper.common.exception.resume.DocumentNotFoundException;
import com.example.whopper.domain.feedback.FeedbackMongoRepository;
import com.example.whopper.domain.library.LibraryMongoRepository;
import com.example.whopper.domain.library.ShardLibrary;
import com.example.whopper.domain.student.StudentEntity;
import com.example.whopper.application.student.component.CurrentStudent;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class FindDocumentService implements FindDocumentUseCase {
    private final DocumentRepository documentRepository;
    private final FeedbackMongoRepository feedbackMongoRepository;
    private final LibraryMongoRepository libraryMongoRepository;
    private final CurrentStudent currentStudent;
    private final TeacherComponent teacherComponent;

    @Override
    public DocumentResponse getIntroduceRecentlySharedDocuments() {
        var currentStudentDocument = currentStudent.getDocument();
        var libraries = libraryMongoRepository.findTop3ByOrderByCreateAtDesc()
                .map(ShardLibrary::fromLibraryEntity)
                .toList();

        return DocumentResponse.of(
                currentStudentDocument,
                libraries
        );
    }

    @Override
    public FullDocumentResponse getCurrentStudentDocument() {
        var currentStudentDocument = currentStudent.getDocument();
        var student = currentStudentDocument.getStudent();
        var majorName = getMajorName(student);

        return FullDocumentResponse.of(currentStudentDocument, majorName);
    }

    @Override
    public FullDocumentResponse getSubmittedDocument(String documentId) {
        var document = documentRepository.findById(documentId)
                .orElseThrow(() -> DocumentNotFoundException.EXCEPTION);

        var student = document.getStudent();
        var majorName = getMajorName(student);

        return FullDocumentResponse.of(document, majorName);
    }

    @Override
    public DataResponseInfo<SearchDocumentResponse> searchDocument(String name, Integer grade, Integer classNumber, String majorId, String status) {
        var teacherId = teacherComponent.currentTeacher().getId();

        var documents = documentRepository.searchDocuments(name, grade, classNumber, majorId, status);
        var documentIds = documents.stream().map(DocumentEntity::getId).toList();

        Map<String, List<FeedbackEntity>> feedbackMap = feedbackMongoRepository.findAllByDocumentIdInAndTeacherId(documentIds, teacherId)
                .stream()
                .collect(Collectors.groupingBy(FeedbackEntity::getDocumentId));

        var responses = documents.stream()
                .map(document -> SearchDocumentResponse.of(
                        document,
                        feedbackMap.get(document.getId())
                                .stream()
                                .map(SearchDocumentResponse.Feedback::fromFeedback)
                                .toList()
                ))
                .toList();

        return DataResponseInfo.of(responses);
    }

    @Override
    public CompletionElementLevel getCurrentStudentDocumentCompletionLevel() {
        var currentStudentDocument = currentStudent.getDocument();

        return CompletionElementLevel.of(currentStudentDocument);
    }

    @Override
    public DataResponseInfo<ReleasedDocumentResponse> getReleasedDocuments() {
        return DataResponseInfo.of(
                documentRepository.getReleasedDocuments()
                        .map(ReleasedDocumentResponse::of)
                        .toList()
        );
    }

    @Override
    public DataResponseInfo<FullDocumentResponse> getReleasedDocumentsByGradeAndYear(int grade, int year) {
        var generation = (year - 2013) - grade;
        var document = documentRepository.getReleasedDocumentsByGenerationAndYear(generation, year);

        var response = document.map(doc -> FullDocumentResponse.of(doc, doc.getStudent().getMajor().getName()))
                .toList();

        return DataResponseInfo.of(response);
    }

    private String getMajorName(StudentEntity student) {
        return student.getMajor() == null ? "전공 미정" : student.getMajor().getName();
    }
}
