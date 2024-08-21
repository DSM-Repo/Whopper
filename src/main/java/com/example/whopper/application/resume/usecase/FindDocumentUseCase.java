package com.example.whopper.application.resume.usecase;

import com.example.whopper.domain.resume.detail.CompletionElementLevel;
import com.example.whopper.interfaces.resume.dto.response.DocumentResponse;
import com.example.whopper.interfaces.resume.dto.response.FullDocumentResponse;
import com.example.whopper.interfaces.resume.dto.response.ReleasedDocumentResponse;
import com.example.whopper.interfaces.resume.dto.response.SearchDocumentResponse;
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
