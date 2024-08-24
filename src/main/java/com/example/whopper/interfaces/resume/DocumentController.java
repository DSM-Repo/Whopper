package com.example.whopper.interfaces.resume;

import com.example.whopper.application.resume.usecase.FindResumeUseCase;
import com.example.whopper.application.resume.usecase.ReleaseDocumentUseCase;
import com.example.whopper.application.resume.usecase.SubmitMyDocumentUseCase;
import com.example.whopper.domain.resume.detail.CompletionElementLevel;
import com.example.whopper.interfaces.resume.dto.response.ResumeResponse;
import com.example.whopper.interfaces.resume.dto.response.FullResumeResponse;
import com.example.whopper.interfaces.resume.dto.response.ReleasedResumeResponse;
import com.example.whopper.interfaces.resume.dto.response.SearchResumeResponse;
import com.example.whopper.common.annotation.OnlyStudent;
import com.example.whopper.common.annotation.OnlyTeacher;
import com.example.whopper.common.http.response.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/document")
class DocumentController {
    private final FindResumeUseCase findResumeUseCase;
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
    DataResponseInfo<SearchResumeResponse> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer grade,
            @RequestParam(required = false) Integer classNumber,
            @RequestParam(required = false) String majorId,
            @RequestParam(required = false) String status
    ) {
        return findResumeUseCase.searchResume(name, grade, classNumber, majorId, status);
    }

    @OnlyTeacher
    @GetMapping("/released")
    DataResponseInfo<ReleasedResumeResponse> getReleasedDocuments() {
        return findResumeUseCase.getReleasedResumes();
    }

    @OnlyTeacher
    @GetMapping("/released/grade/{grade}/year/{year}")
    DataResponseInfo<FullResumeResponse> getReleasedDocumentsByGradeAndYear(@PathVariable Integer grade, @PathVariable Integer year) {
        return findResumeUseCase.getReleasedResumesByGradeAndYear(grade, year);
    }

    @OnlyTeacher
    @GetMapping("/student/{documentId}")
    FullResumeResponse getSubmittedDocument(@PathVariable String documentId) {
        return findResumeUseCase.getSubmittedResume(documentId);
    }

    @OnlyStudent
    @GetMapping("/completion")
    CompletionElementLevel getCompletionLevel() {
        return findResumeUseCase.getCurrentStudentResumeCompletionLevel();
    }

    @OnlyStudent
    @GetMapping
    ResumeResponse getIntroduceRecentlySharedDocuments() {
        return findResumeUseCase.getIntroduceRecentlySharedResumes();
    }

    @OnlyStudent
    @GetMapping("/detail")
    FullResumeResponse getCurrentDocument() {
        return findResumeUseCase.getCurrentStudentResume();
    }
}
