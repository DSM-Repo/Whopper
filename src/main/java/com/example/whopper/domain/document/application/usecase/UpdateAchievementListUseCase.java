package com.example.whopper.domain.document.application.usecase;

import com.example.whopper.domain.document.domain.element.AchievementElement;

import java.util.List;

public interface UpdateAchievementListUseCase {
    void update(List<AchievementElement> request);
}
