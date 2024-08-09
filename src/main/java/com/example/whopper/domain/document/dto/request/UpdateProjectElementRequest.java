package com.example.whopper.domain.document.dto.request;

import com.example.whopper.domain.document.domain.element.type.ProjectType;

import java.util.Set;

public record UpdateProjectElementRequest(String elementId, String name, String imagePath, ProjectType type, String startDate, String endDate, Set<String> skillSet, String description, String url) {
}
