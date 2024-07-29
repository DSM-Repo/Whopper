package com.example.whopper.domain.document.domain.element;

import com.example.whopper.domain.document.domain.element.type.ProjectType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Set;

public record ProjectElement(
        String name,
        String imagePath,
        ProjectType type,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "Asia/Seoul")
        Date startDate,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "Asia/Seoul")
        Date endDate,
        Set<String> skillSet,
        String description,
        Set<String> urls
) {
}
