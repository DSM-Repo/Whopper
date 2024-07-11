package com.example.whopper.domain.document.api;

import com.example.whopper.domain.document.application.base.UpdateElementUseCase;
import com.example.whopper.domain.document.application.impl.*;
import com.example.whopper.domain.document.domain.element.*;
import com.example.whopper.domain.document.dto.request.UpdateListRequest;
import com.example.whopper.domain.document.dto.request.UpdateWriterInfoRequest;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/document")
public class DocumentController {
    private final UpdateElementUseCase<List<ActivityElement>> updateActivityListUseCase;
    private final UpdateElementUseCase<List<AwardElement>> updateAwardListUseCase;
    private final UpdateElementUseCase<List<CertificateElement>> updateCertificateListUseCase;
    private final UpdateElementUseCase<List<ProjectElement>> updateProjectListUseCase;
    private final UpdateElementUseCase<IntroduceElement> updateIntroduceUseCase;
    private final UpdateElementUseCase<UpdateWriterInfoRequest> updateWriterInfoUseCase;

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

    public DocumentController(UpdateActivityListService updateActivityListService, UpdateAwardListService updateAwardListService, UpdateCertificateListService updateCertificateListService, UpdateProjectListService updateProjectListService, UpdateIntroduceService updateIntroduceService, UpdateWriterInfoService updateWriterInfoService) {
        updateActivityListUseCase = updateActivityListService;
        updateAwardListUseCase = updateAwardListService;
        updateCertificateListUseCase = updateCertificateListService;
        updateProjectListUseCase = updateProjectListService;
        updateIntroduceUseCase = updateIntroduceService;
        updateWriterInfoUseCase = updateWriterInfoService;
    }
}
