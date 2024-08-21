package com.example.whopper.lagacy.document.application.usecase;

import com.example.whopper.lagacy.document.dto.AchievementElementDto;

import java.util.List;

public interface UpdateAchievementListUseCase {
    void update(List<AchievementElementDto> request);
}
