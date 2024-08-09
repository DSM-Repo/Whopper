package com.example.whopper.domain.document.application.usecase;

import com.example.whopper.domain.document.dto.request.UpdateAchievementElementRequest;

import java.util.List;

public interface UpdateAchievementListUseCase {
    void update(List<UpdateAchievementElementRequest> request);
}
