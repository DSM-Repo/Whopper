package com.example.whopper.domain.document.application.usecase;

import com.example.whopper.domain.document.dto.response.DocumentResponse;
import com.example.whopper.domain.document.dto.response.FullDocumentResponse;

public interface FindDocumentUseCase {
    DocumentResponse getCurrentStudentDocumentMainPageResponse();
    FullDocumentResponse getFullDocument();
}
