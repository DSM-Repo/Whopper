package com.example.whopper.interfaces.resume.dto;

import com.example.whopper.domain.resume.element.ProjectElement;
import com.example.whopper.domain.resume.element.type.ProjectType;
import com.example.whopper.domain.file.ImageInfo;

import java.util.Set;

public record ProjectElementDto(String elementId, String name, ImageInfo imageInfo, ProjectType type, String startDate, String endDate, Set<String> skillSet, ProjectElement.ProjectDescription description, String url) {
    public static ProjectElementDto fromEntity(ProjectElement element) {
        return new ProjectElementDto(
                element.getElementId(),
                element.getName(),
                element.getImageInfo(),
                element.getType(),
                element.getStartDate(),
                element.getEndDate(),
                element.getSkillSet(),
                element.getDescription(),
                element.getUrl()
        );
    }
}