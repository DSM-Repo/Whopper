package com.example.whopper.domain.document.dto;

import com.example.whopper.domain.document.domain.element.ProjectElement;
import com.example.whopper.domain.document.domain.element.type.ProjectType;

import java.util.Set;

public record ProjectElementDto(String elementId, String name, String imagePath, ProjectType type, String startDate, String endDate, Set<String> skillSet, String description, String url) {
    public static ProjectElementDto fromEntity(ProjectElement element) {
        return new ProjectElementDto(
                element.getElementId(),
                element.getName(),
                element.getImagePath(),
                element.getType(),
                element.getStartDate(),
                element.getEndDate(),
                element.getSkillSet(),
                element.getDescription(),
                element.getUrl()
        );
    }
}
