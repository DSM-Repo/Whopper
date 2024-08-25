package com.example.whopper.application.resume.usecase;

import com.example.whopper.interfaces.resume.dto.ResumeElementDto;

import java.util.List;

public interface UpdateAchievementListUseCase {
    void update(List<ResumeElementDto.Achievement> request);
}
