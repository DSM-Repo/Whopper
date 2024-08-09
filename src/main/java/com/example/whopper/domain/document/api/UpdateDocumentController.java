package com.example.whopper.domain.document.api;

import com.example.whopper.domain.document.application.usecase.*;
import com.example.whopper.domain.document.dto.request.*;
import com.example.whopper.global.annotation.OnlyStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/document")
public class UpdateDocumentController {
    private final UpdateActivityListUseCase updateActivityListUseCase;
    private final UpdateAchievementListUseCase updateAchievementListUseCase;
    private final UpdateProjectListUseCase updateProjectListUseCase;
    private final UpdateIntroduceUseCase updateIntroduceUseCase;
    private final UpdateWriterInfoUseCase updateWriterInfoUseCase;

    @OnlyStudent
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/writer-info")
    public void updateWriterInfo(@RequestBody UpdateWriterInfoRequest request) {
        updateWriterInfoUseCase.update(request);
    }

    @OnlyStudent
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/introduce")
    public void updateIntroduce(@RequestBody UpdateIntroduceElementRequest request) {
        updateIntroduceUseCase.update(request);
    }

    @OnlyStudent
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/project")
    public void updateProjectList(@RequestBody UpdateListRequest<UpdateProjectElementRequest> request) {
        updateProjectListUseCase.update(request.list());
    }

    @OnlyStudent
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/achievement")
    public void updateAchievementList(@RequestBody UpdateListRequest<UpdateAchievementElementRequest> request) {
        updateAchievementListUseCase.update(request.list());
    }

    @OnlyStudent
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/activity")
    public void updateActivityList(@RequestBody UpdateListRequest<UpdateActivityElementRequest> request) {
        updateActivityListUseCase.update(request.list());
    }
}
