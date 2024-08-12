package com.example.whopper.domain.document.dto;

import com.example.whopper.domain.document.domain.element.AchievementElement;
import com.example.whopper.domain.document.domain.element.type.AchievementType;

public record AchievementElementDto(String elementId, String name, String institution, String date, AchievementType type) {
    public static AchievementElementDto fromEntity(AchievementElement element) {
        return new AchievementElementDto(
                element.getElementId(),
                element.getName(),
                element.getInstitution(),
                element.getDate(),
                element.getType()
        );
    }
}
