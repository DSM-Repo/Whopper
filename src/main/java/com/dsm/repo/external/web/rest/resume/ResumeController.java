package com.dsm.repo.external.web.rest.resume;

import com.dsm.repo.internal.core.usecase.resume.FindResumeUseCase;
import com.dsm.repo.internal.core.usecase.resume.ReleaseResumeUseCase;
import com.dsm.repo.internal.core.usecase.resume.SubmitMyResumeUseCase;
import com.dsm.repo.external.web.rest.resume.dto.response.CompletionElementLevelResponse;
import com.dsm.repo.external.web.rest.resume.dto.response.ResumeResponse;
import com.dsm.repo.external.web.rest.resume.dto.response.FullResumeResponse;
import com.dsm.repo.external.web.rest.resume.dto.response.ReleasedResumeResponse;
import com.dsm.repo.external.web.rest.resume.dto.response.SearchResumeResponse;
import com.dsm.repo.external.security.auth.annotation.OnlyStudent;
import com.dsm.repo.external.security.auth.annotation.OnlyTeacher;
import com.dsm.repo.external.web.rest.common.DataResponseInfo;
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
    DataResponseInfo<FullResumeResponse> getReleasedResumesByGradeAndYear(
            @PathVariable Integer grade,
            @PathVariable Integer year
    ) {
        return findResumeUseCase.getReleasedResumesByGradeAndYear(grade, year);
    }

    @GetMapping("/student/{resumeId}")
    FullResumeResponse getSubmittedResume(@PathVariable String resumeId) {
        return findResumeUseCase.getSubmittedResume(resumeId);
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
