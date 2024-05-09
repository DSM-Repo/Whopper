package com.example.whopper.domain.document.domain.element;

import com.example.whopper.domain.document.domain.element.type.ProjectType;

import java.util.Date;
import java.util.Set;

public record ProjectElement(
        String name,
        String imagePath,
        ProjectType type,
        Date startDate,
        Date endDate,
        Set<String> skillSet,
        String description,
        Set<String> urls
) {
}
