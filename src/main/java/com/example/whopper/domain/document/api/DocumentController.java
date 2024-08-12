package com.example.whopper.domain.document.api;

import com.example.whopper.domain.document.application.usecase.*;
import com.example.whopper.domain.document.domain.detail.CompletionElementLevel;
import com.example.whopper.domain.document.dto.response.DocumentResponse;
import com.example.whopper.domain.document.dto.response.FullDocumentResponse;
import com.example.whopper.domain.document.dto.response.ReleasedDocumentResponse;
import com.example.whopper.domain.document.dto.response.SearchDocumentResponse;
import com.example.whopper.global.annotation.OnlyStudent;
import com.example.whopper.global.annotation.OnlyTeacher;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/document")
public class DocumentController {
    private final FindDocumentUseCase findDocumentUseCase;
    private final SubmitMyDocumentUseCase submitMyDocumentUseCase;
    private final ReleaseDocumentUseCase releaseDocumentUseCase;

    @OnlyTeacher
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/release/{documentId}")
    public void release(@PathVariable String documentId) {
        releaseDocumentUseCase.release(documentId);
    }

    @OnlyStudent
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/submit")
    public void submit() {
        submitMyDocumentUseCase.submit();
    }

    @OnlyTeacher
    @GetMapping("/student")
    public DataResponseInfo<SearchDocumentResponse> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer grade,
            @RequestParam(required = false) Integer classNumber,
            @RequestParam(required = false) String majorId,
            @RequestParam(required = false) String status
    ) {
        return findDocumentUseCase.searchDocument(name, grade, classNumber, majorId, status);
    }

    @GetMapping("/release")
    public DataResponseInfo<ReleasedDocumentResponse> getReleasedDocuments() {
        return findDocumentUseCase.getReleasedDocuments();
    }

    @GetMapping("/release/{documentId}")
    public FullDocumentResponse findReleasedDocument(@PathVariable String documentId) {
        return findDocumentUseCase.findReleasedDocument(documentId);
    }

    @OnlyTeacher
    @GetMapping("/student/{documentId}")
    public FullDocumentResponse getSubmittedDocument(@PathVariable String documentId) {
        return findDocumentUseCase.getSubmittedDocument(documentId);
    }

    @OnlyStudent
    @GetMapping("/completion")
    public CompletionElementLevel getCompletionLevel() {
        return findDocumentUseCase.getCurrentStudentDocumentCompletionLevel();
    }

    @OnlyStudent
    @GetMapping
    public DocumentResponse getIntroduceRecentlySharedDocuments() {
        return findDocumentUseCase.getIntroduceRecentlySharedDocuments();
    }

    @OnlyStudent
    @GetMapping("/detail")
    public FullDocumentResponse getCurrentDocument() {
        return findDocumentUseCase.getCurrentStudentDocument();
    }
}
