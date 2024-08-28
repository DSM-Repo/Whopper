package com.repo.whopper.application.resume.service;

import com.repo.whopper.application.resume.base.AbstractUpdateElementServiceBase;
import com.repo.whopper.application.resume.usecase.UpdateAchievementListUseCase;
import com.repo.whopper.domain.resume.ResumeRepository;
import com.repo.whopper.domain.resume.ResumeModel;
import com.repo.whopper.application.student.component.CurrentStudent;
import com.repo.whopper.interfaces.resume.dto.ResumeElementDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class UpdateAchievementListService extends AbstractUpdateElementServiceBase<List<ResumeElementDto.Achievement>> implements UpdateAchievementListUseCase {
    public UpdateAchievementListService(ResumeRepository resumeRepository, CurrentStudent currentUser) {
        super(resumeRepository, currentUser);
    }

    @Override
    protected ResumeModel updateResume(ResumeModel resume, List<ResumeElementDto.Achievement> achievementList) {
        return resume.updateAchievementList(achievementList);
    }
}
