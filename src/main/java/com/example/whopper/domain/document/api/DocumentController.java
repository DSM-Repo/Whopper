package com.example.whopper.domain.document.api;

import com.example.whopper.domain.document.application.usecase.*;
import com.example.whopper.domain.document.domain.element.*;
import com.example.whopper.domain.document.dto.request.UpdateListRequest;
import com.example.whopper.domain.document.dto.request.UpdateWriterInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/document")
public class DocumentController {
    private final UpdateActivityListUseCase updateActivityListUseCase;
    private final UpdateAwardListUseCase updateAwardListUseCase;
    private final UpdateCertificateListUseCase updateCertificateListUseCase;
    private final UpdateProjectListUseCase updateProjectListUseCase;
    private final UpdateIntroduceUseCase updateIntroduceUseCase;
    private final UpdateWriterInfoUseCase updateWriterInfoUseCase;

    @PatchMapping("/writer-info")
    public void updateWriterInfo(@RequestBody UpdateWriterInfoRequest request) {
        updateWriterInfoUseCase.update(request);
    }

    @PatchMapping("/introduce")
    public void updateIntroduce(@RequestBody IntroduceElement request) {
        updateIntroduceUseCase.update(request);
    }

    @PatchMapping("/project")
    public void updateProjectList(@RequestBody UpdateListRequest<ProjectElement> request) {
        updateProjectListUseCase.update(request.list());
    }

    @PatchMapping("/award")
    public void updateAwardList(@RequestBody UpdateListRequest<AwardElement> request) {
        updateAwardListUseCase.update(request.list());
    }

    @PatchMapping("/certificate")
    public void updateCertificateList(@RequestBody UpdateListRequest<CertificateElement> request) {
        updateCertificateListUseCase.update(request.list());
    }

    @PatchMapping("/activity")
    public void updateActivityList(@RequestBody UpdateListRequest<ActivityElement> request) {
        updateActivityListUseCase.update(request.list());
    }
}
