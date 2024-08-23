package com.example.whopper.interfaces.resume.dto;

import com.example.whopper.domain.resume.element.ActivityElement;

public record ActivityElementDto(String elementId, String name, String startDate, String endDate, boolean isPeriod, String description) {
    public static ActivityElementDto fromEntity(ActivityElement element) {
        return new ActivityElementDto(
                element.getElementId(),
                element.getName(),
                element.getStartDate(),
                element.getEndDate(),
                element.isPeriod(),
                element.getDescription()
        );
    }
}
