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
import com.example.whopper.domain.major.dao.MajorRepository;
import com.example.whopper.global.utils.current.CurrentStudent;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindDocumentService implements FindDocumentUseCase {
    private final DocumentRepository documentRepository;
    private final MajorRepository majorRepository;
    private final FeedbackMongoRepository feedbackMongoRepository;
    private final CurrentStudent currentStudent;

    @Override
    public DocumentResponse getIntroduceRecentlySharedDocuments() {
        var currentStudentDocument = currentStudent.getDocument();

        return DocumentResponse.of(
                currentStudentDocument,
                List.of() // 최근 공유된 document
        );
    }

    @Override
    public FullDocumentResponse getCurrentStudentDocument() {
        var currentStudentDocument = currentStudent.getDocument();
        var student = currentStudentDocument.getStudent();
        var major = majorRepository.getById(student.getMajorId());

        return FullDocumentResponse.of(currentStudentDocument, major.name());
    }

    @Override
    public FullDocumentResponse getSubmittedDocument(String documentId) {
        var document = documentRepository.findById(documentId)
                .orElseThrow(() -> DocumentNotFoundException.EXCEPTION);

        var student = document.getStudent();
        var major = majorRepository.getById(student.getMajorId());

        return FullDocumentResponse.of(document, major.name());
    }

    @Override
    public DataResponseInfo<SearchDocumentResponse> searchDocument(SearchDocumentRequest request) {
        return DataResponseInfo.of(
                documentRepository.searchDocument(request)
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
    public FullDocumentResponse findReleasedDocument(String documentId) {
        var document = documentRepository.findById(documentId)
                .orElseThrow(() -> DocumentNotFoundException.EXCEPTION);

        if (!document.getStatus().equals(DocumentStatus.RELEASED)) {
            throw DocumentIllegalStatusException.EXCEPTION;
        }

        return FullDocumentResponse.of(document, majorRepository.getById(document.getStudent().getMajorId()).name());
    }
}
