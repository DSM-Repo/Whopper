package com.example.whopper.application.resume.usecase;

import com.example.whopper.interfaces.resume.dto.AchievementElementDto;

import java.util.List;

public interface UpdateAchievementListUseCase {
    void update(List<AchievementElementDto> request);
}
