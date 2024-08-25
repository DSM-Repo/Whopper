package com.example.whopper.application.resume.service;

import com.example.whopper.application.resume.base.AbstractUpdateElementServiceBase;
import com.example.whopper.application.resume.usecase.UpdateIntroduceUseCase;
import com.example.whopper.domain.resume.ResumeRepository;
import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.application.student.component.CurrentStudent;
import com.example.whopper.interfaces.resume.dto.ResumeElementDto;
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
