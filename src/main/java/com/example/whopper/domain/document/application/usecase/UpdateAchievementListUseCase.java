package com.example.whopper.domain.document.application.usecase;

import com.example.whopper.domain.document.dto.AchievementElementDto;

import java.util.List;

public interface UpdateAchievementListUseCase {
    void update(List<AchievementElementDto> request);
}
