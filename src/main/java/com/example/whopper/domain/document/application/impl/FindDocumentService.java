package com.example.whopper.domain.document.application.impl;

import com.example.whopper.domain.document.application.usecase.FindDocumentUseCase;
import com.example.whopper.domain.document.domain.detail.CompletionElementLevel;
import com.example.whopper.domain.document.dto.response.DocumentResponse;
import com.example.whopper.domain.document.dto.response.FullDocumentResponse;
import com.example.whopper.global.utils.current.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindDocumentService implements FindDocumentUseCase {
    private final CurrentUser currentUser;

    @Override
    public DocumentResponse getCurrentStudentDocumentMainPageResponse() {
        var currentStudentDocument = currentUser.getDocument();
        var currentStudent = currentStudentDocument.getStudent();

        return DocumentResponse.of(
                currentStudent,
                currentStudentDocument,
                List.of() // 최근 공유된 document
        );
    }

    @Override
    public FullDocumentResponse getFullDocument() {
        var currentStudentDocument = currentUser.getDocument();
        var currentStudent = currentStudentDocument.getStudent();

        return FullDocumentResponse.of(currentStudent, currentStudentDocument);
    }
}
