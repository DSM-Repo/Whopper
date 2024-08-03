package com.example.whopper.domain.document.application.impl;

import com.example.whopper.domain.document.application.usecase.FindDocumentUseCase;
import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.detail.CompletionElementLevel;
import com.example.whopper.domain.document.dto.request.SearchDocumentRequest;
import com.example.whopper.domain.document.dto.response.DocumentResponse;
import com.example.whopper.domain.document.dto.response.FullDocumentResponse;
import com.example.whopper.domain.document.dto.response.SearchDocumentResponse;
import com.example.whopper.domain.document.exception.DocumentNotFoundException;
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
    private final CurrentStudent currentStudent;

    @Override
    public DocumentResponse getCurrentStudentDocumentMainPageResponse() {
        var currentStudentDocument = currentStudent.getDocument();

        return DocumentResponse.of(
                currentStudentDocument,
                List.of() // 최근 공유된 document
        );
    }

    @Override
    public FullDocumentResponse getCurrentStudentDocument() {
        var currentStudentDocument = currentStudent.getDocument();
        var currentStudent = currentStudentDocument.getStudent();
        var major = majorRepository.getById(currentStudent.getMajorId());

        return FullDocumentResponse.of(currentStudentDocument, currentStudent, major.name());
    }

    @Override
    public FullDocumentResponse getSubmittedDocument(String documentId) {
        var document = documentRepository.findById(documentId)
                .orElseThrow(() -> DocumentNotFoundException.EXCEPTION);

        var student = document.getStudent();
        var major = majorRepository.getById(student.getMajorId());

        return FullDocumentResponse.of(document, student, major.name());
    }

    @Override
    public DataResponseInfo<SearchDocumentResponse> searchDocument(SearchDocumentRequest request) {
        return DataResponseInfo.of(
                documentRepository.searchDocument(request)
                        .map(SearchDocumentResponse::of)
                        .toList()
        );
    }

    @Override
    public CompletionElementLevel getCurrentStudentDocumentCompletionLevel() {
        var currentStudentDocument = currentStudent.getDocument();

        return CompletionElementLevel.of(currentStudentDocument);
    }
}
