package com.example.whopper.interfaces.resume.dto;

import com.example.whopper.domain.resume.element.AchievementElement;
import com.example.whopper.domain.resume.element.type.AchievementType;

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
