package com.example.whopper.application.resume.service;

import com.example.whopper.application.resume.base.AbstractUpdateElementServiceBase;
import com.example.whopper.application.resume.usecase.UpdateAchievementListUseCase;
import com.example.whopper.domain.resume.ResumeRepository;
import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.application.student.component.CurrentStudent;
import com.example.whopper.interfaces.resume.dto.ResumeElementDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class UpdateAchievementListService extends AbstractUpdateElementServiceBase<List<ResumeElementDto.Achievement>> implements UpdateAchievementListUseCase {
    public UpdateAchievementListService(ResumeRepository resumeRepository, CurrentStudent currentUser) {
        super(resumeRepository, currentUser);
    }

    @Override
    protected ResumeModel updateResume(ResumeModel resume, List<ResumeElementDto.Achievement> list) {
        return resume.updateAchievementList(list);
    }
}
