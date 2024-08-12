package com.example.whopper.domain.document.application.impl;

import com.example.whopper.domain.document.application.usecase.FindDocumentUseCase;
import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.detail.CompletionElementLevel;
import com.example.whopper.domain.document.domain.element.DocumentStatus;
import com.example.whopper.domain.document.dto.request.SearchDocumentRequest;
import com.example.whopper.domain.document.dto.response.DocumentResponse;
import com.example.whopper.domain.document.dto.response.FullDocumentResponse;
import com.example.whopper.domain.document.dto.response.ReleasedDocumentResponse;
import com.example.whopper.domain.document.dto.response.SearchDocumentResponse;
import com.example.whopper.domain.document.exception.DocumentIllegalStatusException;
import com.example.whopper.domain.document.exception.DocumentNotFoundException;
import com.example.whopper.domain.feedback.dao.FeedbackMongoRepository;
import com.example.whopper.domain.library.api.LibraryController;
import com.example.whopper.domain.library.dao.LibraryMongoRepository;
import com.example.whopper.domain.library.domain.ShardLibrary;
import com.example.whopper.domain.student.domain.StudentEntity;
import com.example.whopper.global.utils.current.CurrentStudent;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindDocumentService implements FindDocumentUseCase {
    private final DocumentRepository documentRepository;
    private final FeedbackMongoRepository feedbackMongoRepository;
    private final LibraryMongoRepository libraryMongoRepository;
    private final CurrentStudent currentStudent;

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
        return DataResponseInfo.of(
                documentRepository.searchDocuments(name, grade, classNumber, majorId, status)
                        .map(document -> SearchDocumentResponse.of(
                                document,
                                feedbackMongoRepository.countByDocument(document)
                        ))
                        .toList()
        );
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
