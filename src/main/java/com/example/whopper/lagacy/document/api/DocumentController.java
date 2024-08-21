package com.example.whopper.lagacy.document.api;

import com.example.whopper.lagacy.document.application.usecase.*;
import com.example.whopper.lagacy.document.domain.detail.CompletionElementLevel;
import com.example.whopper.lagacy.document.dto.response.DocumentResponse;
import com.example.whopper.lagacy.document.dto.response.FullDocumentResponse;
import com.example.whopper.lagacy.document.dto.response.ReleasedDocumentResponse;
import com.example.whopper.lagacy.document.dto.response.SearchDocumentResponse;
import com.example.whopper.common.annotation.OnlyStudent;
import com.example.whopper.common.annotation.OnlyTeacher;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/document")
class DocumentController {
    private final FindDocumentUseCase findDocumentUseCase;
    private final SubmitMyDocumentUseCase submitMyDocumentUseCase;
    private final ReleaseDocumentUseCase releaseDocumentUseCase;

    @OnlyTeacher
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/release/{documentId}")
    void release(@PathVariable String documentId) {
        releaseDocumentUseCase.release(documentId);
    }

    @OnlyStudent
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/submit")
    void submit() {
        submitMyDocumentUseCase.submit();
    }

    @OnlyTeacher
    @GetMapping("/student")
    DataResponseInfo<SearchDocumentResponse> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer grade,
            @RequestParam(required = false) Integer classNumber,
            @RequestParam(required = false) String majorId,
            @RequestParam(required = false) String status
    ) {
        return findDocumentUseCase.searchDocument(name, grade, classNumber, majorId, status);
    }

    @OnlyTeacher
    @GetMapping("/released")
    DataResponseInfo<ReleasedDocumentResponse> getReleasedDocuments() {
        return findDocumentUseCase.getReleasedDocuments();
    }

    @OnlyTeacher
    @GetMapping("/released/grade/{grade}/year/{year}")
    DataResponseInfo<FullDocumentResponse> getReleasedDocumentsByGradeAndYear(@PathVariable Integer grade, @PathVariable Integer year) {
        return findDocumentUseCase.getReleasedDocumentsByGradeAndYear(grade, year);
    }

    @OnlyTeacher
    @GetMapping("/student/{documentId}")
    FullDocumentResponse getSubmittedDocument(@PathVariable String documentId) {
        return findDocumentUseCase.getSubmittedDocument(documentId);
    }

    @OnlyStudent
    @GetMapping("/completion")
    CompletionElementLevel getCompletionLevel() {
        return findDocumentUseCase.getCurrentStudentDocumentCompletionLevel();
    }

    @OnlyStudent
    @GetMapping
    DocumentResponse getIntroduceRecentlySharedDocuments() {
        return findDocumentUseCase.getIntroduceRecentlySharedDocuments();
    }

    @OnlyStudent
    @GetMapping("/detail")
    FullDocumentResponse getCurrentDocument() {
        return findDocumentUseCase.getCurrentStudentDocument();
    }
}
