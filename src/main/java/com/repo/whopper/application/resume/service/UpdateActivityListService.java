package com.repo.whopper.application.resume.service;

import com.repo.whopper.application.resume.base.AbstractUpdateElementServiceBase;
import com.repo.whopper.application.resume.usecase.UpdateActivityListUseCase;
import com.repo.whopper.domain.resume.ResumeRepository;
import com.repo.whopper.domain.resume.ResumeModel;
import com.repo.whopper.application.student.component.CurrentStudent;
import com.repo.whopper.interfaces.resume.dto.ResumeElementDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class UpdateActivityListService extends AbstractUpdateElementServiceBase<List<ResumeElementDto.Activity>> implements UpdateActivityListUseCase {
    public UpdateActivityListService(ResumeRepository resumeRepository, CurrentStudent currentUser) {
        super(resumeRepository, currentUser);
    }

    @Override
    protected ResumeModel updateResume(ResumeModel resume, List<ResumeElementDto.Activity> activityList) {
        return resume.updateActivityList(activityList);
    }
}
