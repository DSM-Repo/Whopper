package com.repo.whopper.interfaces.resume;

import com.repo.whopper.application.resume.usecase.UpdateAchievementListUseCase;
import com.repo.whopper.application.resume.usecase.UpdateActivityListUseCase;
import com.repo.whopper.application.resume.usecase.UpdateIntroduceUseCase;
import com.repo.whopper.application.resume.usecase.UpdateProjectListUseCase;
import com.repo.whopper.application.resume.usecase.UpdateWriterInfoUseCase;
import com.repo.whopper.interfaces.resume.dto.*;
import com.repo.whopper.interfaces.resume.dto.request.UpdateListRequest;
import com.repo.whopper.interfaces.resume.dto.request.UpdateWriterInfoRequest;
import com.repo.whopper.common.annotation.OnlyStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resume")
class UpdateResumeController {
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
    void updateIntroduce(@RequestBody ResumeElementDto.Introduce request) {
        updateIntroduceUseCase.update(request);
    }

    @OnlyStudent
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/project")
    void updateProjectList(@RequestBody UpdateListRequest<ResumeElementDto.Project> request) {
        updateProjectListUseCase.update(request.list());
    }

    @OnlyStudent
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/achievement")
    void updateAchievementList(@RequestBody UpdateListRequest<ResumeElementDto.Achievement> request) {
        updateAchievementListUseCase.update(request.list());
    }

    @OnlyStudent
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/activity")
    void updateActivityList(@RequestBody UpdateListRequest<ResumeElementDto.Activity> request) {
        updateActivityListUseCase.update(request.list());
    }
}
