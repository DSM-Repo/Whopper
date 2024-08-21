package com.example.whopper.domain.document.api;

import com.example.whopper.domain.document.application.usecase.*;
import com.example.whopper.domain.document.dto.*;
import com.example.whopper.domain.document.dto.request.*;
import com.example.whopper.global.annotation.OnlyStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/document")
class UpdateDocumentController {
    private final UpdateActivityListUseCase updateActivityListUseCase;
    private final UpdateAchievementListUseCase updateAchievementListUseCase;
    private final UpdateProjectListUseCase updateProjectListUseCase;
    private final UpdateIntroduceUseCase updateIntroduceUseCase;
    private final UpdateWriterInfoUseCase updateWriterInfoUseCase;

    @OnlyStudent
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/writer-info")
    void updateWriterInfo(@RequestBody UpdateWriterInfoRequest request) {
        updateWriterInfoUseCase.update(request);
    }

    @OnlyStudent
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/introduce")
    void updateIntroduce(@RequestBody IntroduceElementDto request) {
        updateIntroduceUseCase.update(request);
    }

    @OnlyStudent
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/project")
    void updateProjectList(@RequestBody UpdateListRequest<ProjectElementDto> request) {
        updateProjectListUseCase.update(request.list());
    }

    @OnlyStudent
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/achievement")
    void updateAchievementList(@RequestBody UpdateListRequest<AchievementElementDto> request) {
        updateAchievementListUseCase.update(request.list());
    }

    @OnlyStudent
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/activity")
    void updateActivityList(@RequestBody UpdateListRequest<ActivityElementDto> request) {
        updateActivityListUseCase.update(request.list());
    }
}
