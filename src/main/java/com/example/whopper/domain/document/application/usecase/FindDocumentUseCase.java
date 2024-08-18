package com.example.whopper.domain.document.application.usecase;

import  com.example.whopper.domain.document.domain.detail.CompletionElementLevel;
import com.example.whopper.domain.document.dto.response.DocumentResponse;
import com.example.whopper.domain.document.dto.response.FullDocumentResponse;
import com.example.whopper.domain.document.dto.response.ReleasedDocumentResponse;
import com.example.whopper.domain.document.dto.response.SearchDocumentResponse;
import com.example.whopper.global.utils.DataResponseInfo;

public interface FindDocumentUseCase {
    DocumentResponse getIntroduceRecentlySharedDocuments();
    FullDocumentResponse getCurrentStudentDocument();
    FullDocumentResponse getSubmittedDocument(String documentId);
    DataResponseInfo<SearchDocumentResponse> searchDocument(String name, Integer grade, Integer classNumber, String majorId, String status);
    CompletionElementLevel getCurrentStudentDocumentCompletionLevel();
    DataResponseInfo<ReleasedDocumentResponse> getReleasedDocuments();
    DataResponseInfo<FullDocumentResponse> getReleasedDocumentsByGradeAndYear(int grade, int year);
}
