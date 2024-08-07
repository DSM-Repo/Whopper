package com.example.whopper.domain.document.api;

import com.example.whopper.domain.document.application.usecase.*;
import com.example.whopper.domain.document.domain.detail.CompletionElementLevel;
import com.example.whopper.domain.document.domain.element.*;
import com.example.whopper.domain.document.dto.request.SearchDocumentRequest;
import com.example.whopper.domain.document.dto.request.UpdateListRequest;
import com.example.whopper.domain.document.dto.request.UpdateWriterInfoRequest;
import com.example.whopper.domain.document.dto.response.DocumentResponse;
import com.example.whopper.domain.document.dto.response.FullDocumentResponse;
import com.example.whopper.domain.document.dto.response.SearchDocumentResponse;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/document")
public class DocumentController {
    private final UpdateActivityListUseCase updateActivityListUseCase;
    private final UpdateAchievementListUseCase updateAchievementListUseCase;
    private final UpdateProjectListUseCase updateProjectListUseCase;
    private final UpdateIntroduceUseCase updateIntroduceUseCase;
    private final UpdateWriterInfoUseCase updateWriterInfoUseCase;
    private final FindDocumentUseCase findDocumentUseCase;
    private final SubmitMyDocumentUseCase submitMyDocumentUseCase;
    private final CancelSubmitMyDocumentUseCase cancelSubmitMyDocumentUseCase;

    @PatchMapping("/submit")
    public void submit() {
        submitMyDocumentUseCase.submit();
    }

    @PatchMapping("/submit/cancel")
    public void cancelSubmit() {
        cancelSubmitMyDocumentUseCase.cancel();
    }

    @GetMapping("/student")
    public DataResponseInfo<SearchDocumentResponse> search(@ModelAttribute SearchDocumentRequest request) {
        return findDocumentUseCase.searchDocument(request);
    }

    @GetMapping("/completion")
    public CompletionElementLevel getCompletionLevel() {
        return findDocumentUseCase.getCurrentStudentDocumentCompletionLevel();
    }

    @GetMapping
    public DocumentResponse getMainPageResponse() {
        return findDocumentUseCase.getCurrentStudentDocumentMainPageResponse();
    }

    @GetMapping("/detail")
    public FullDocumentResponse getCurrentDocument() {
        return findDocumentUseCase.getCurrentStudentDocument();
    }

    @PatchMapping("/writer-info")
    public void updateWriterInfo(@RequestBody UpdateWriterInfoRequest request) {
        updateWriterInfoUseCase.update(request);
    }

    @PatchMapping("/introduce")
    public void updateIntroduce(@RequestBody IntroduceElement request) {
        updateIntroduceUseCase.update(request);
    }

    @PatchMapping("/project")
    public void updateProjectList(
            @RequestPart("projectList") UpdateListRequest<ProjectElement> request
    ) {
        updateProjectListUseCase.update(request.list());
    }

    @PatchMapping("/achievement")
    public void updateAchievementList(@RequestBody UpdateListRequest<AchievementElement> request) {
        updateAchievementListUseCase.update(request.list());
    }

    @PatchMapping("/activity")
    public void updateActivityList(@RequestBody UpdateListRequest<ActivityElement> request) {
        updateActivityListUseCase.update(request.list());
    }
}
