package com.example.whopper.domain.document.dto.request;

import com.example.whopper.domain.document.domain.element.type.AchievementType;

public record UpdateAchievementElementRequest(String elementId, String name, String institution, String date, AchievementType type) {
}
