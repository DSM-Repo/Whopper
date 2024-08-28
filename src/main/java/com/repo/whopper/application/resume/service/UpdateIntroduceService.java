package com.repo.whopper.application.resume.service;

import com.repo.whopper.application.resume.base.AbstractUpdateElementServiceBase;
import com.repo.whopper.application.resume.usecase.UpdateIntroduceUseCase;
import com.repo.whopper.domain.resume.ResumeRepository;
import com.repo.whopper.domain.resume.ResumeModel;
import com.repo.whopper.application.student.component.CurrentStudent;
import com.repo.whopper.interfaces.resume.dto.ResumeElementDto;
import org.springframework.stereotype.Service;

@Service
class UpdateIntroduceService extends AbstractUpdateElementServiceBase<ResumeElementDto.Introduce> implements UpdateIntroduceUseCase {
    public UpdateIntroduceService(ResumeRepository resumeRepository, CurrentStudent currentUser) {
        super(resumeRepository, currentUser);
    }

    @Override
    protected ResumeModel updateResume(ResumeModel resume, ResumeElementDto.Introduce request) {
        return resume.updateIntroduceElement(request);
    }
}
