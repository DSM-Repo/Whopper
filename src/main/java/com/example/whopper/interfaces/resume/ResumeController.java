package com.example.whopper.interfaces.resume;

import com.example.whopper.application.resume.usecase.FindResumeUseCase;
import com.example.whopper.application.resume.usecase.ReleaseResumeUseCase;
import com.example.whopper.application.resume.usecase.SubmitMyResumeUseCase;
import com.example.whopper.interfaces.resume.dto.response.CompletionElementLevelResponse;
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
@RequestMapping("/resume")
class ResumeController {
    private final FindResumeUseCase findResumeUseCase;
    private final SubmitMyResumeUseCase submitMyResumeUseCase;
    private final ReleaseResumeUseCase releaseResumeUseCase;

    @OnlyTeacher
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/release/{resumeId}")
    void release(@PathVariable String resumeId) {
        releaseResumeUseCase.release(resumeId);
    }

    @OnlyStudent
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/submit")
    void submit() {
        submitMyResumeUseCase.submit();
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
    DataResponseInfo<ReleasedResumeResponse> getReleasedResumes() {
        return findResumeUseCase.getReleasedResumes();
    }

    @OnlyTeacher
    @GetMapping("/released/grade/{grade}/year/{year}")
    DataResponseInfo<FullResumeResponse> getReleasedResumesByGradeAndYear(@PathVariable Integer grade, @PathVariable Integer year) {
        return findResumeUseCase.getReleasedResumesByGradeAndYear(grade, year);
    }

    @OnlyTeacher
    @GetMapping("/student/{documentId}")
    FullResumeResponse getSubmittedResume(@PathVariable String documentId) {
        return findResumeUseCase.getSubmittedResume(documentId);
    }

    @OnlyStudent
    @GetMapping("/completion")
    CompletionElementLevelResponse getCompletionLevel() {
        return findResumeUseCase.getCurrentStudentResumeCompletionLevel();
    }

    @OnlyStudent
    @GetMapping
    ResumeResponse getIntroduceRecentlySharedResumes() {
        return findResumeUseCase.getIntroduceRecentlySharedResumes();
    }

    @OnlyStudent
    @GetMapping("/detail")
    FullResumeResponse getCurrentResume() {
        return findResumeUseCase.getCurrentStudentResume();
    }
}
