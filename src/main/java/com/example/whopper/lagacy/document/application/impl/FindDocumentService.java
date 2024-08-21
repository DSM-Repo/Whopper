package com.example.whopper.lagacy.document.application.impl;

import com.example.whopper.lagacy.document.application.usecase.FindDocumentUseCase;
import com.example.whopper.lagacy.document.dao.DocumentRepository;
import com.example.whopper.lagacy.document.domain.detail.CompletionElementLevel;
import com.example.whopper.lagacy.document.dto.response.DocumentResponse;
import com.example.whopper.lagacy.document.dto.response.FullDocumentResponse;
import com.example.whopper.lagacy.document.dto.response.ReleasedDocumentResponse;
import com.example.whopper.lagacy.document.dto.response.SearchDocumentResponse;
import com.example.whopper.lagacy.document.exception.DocumentNotFoundException;
import com.example.whopper.lagacy.feedback.dao.FeedbackMongoRepository;
import com.example.whopper.lagacy.library.dao.LibraryMongoRepository;
import com.example.whopper.lagacy.library.domain.ShardLibrary;
import com.example.whopper.lagacy.student.domain.StudentEntity;
import com.example.whopper.global.utils.current.CurrentStudent;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class FindDocumentService implements FindDocumentUseCase {
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
                                feedbackMongoRepository.countByDocumentId(document.getId())
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
