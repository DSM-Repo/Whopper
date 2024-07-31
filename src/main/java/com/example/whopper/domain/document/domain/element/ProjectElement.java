package com.example.whopper.domain.document.domain.element;

import com.example.whopper.domain.document.domain.element.type.ProjectType;
import com.example.whopper.domain.document.dto.request.ProjectElementRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;
import java.util.Set;

public record ProjectElement(
        String elementId,
        String name,
        String imagePath,
        ProjectType type,
        String startDate,
        String endDate,
        Set<String> skillSet,
        String description,
        Set<String> urls
) {
        public ProjectElement {
                if (elementId == null) {
                        elementId = new ObjectId().toHexString();
                }
        }
        public static ProjectElement fromProjectElementRequest(ProjectElementRequest request, String imagePath) {
                return new ProjectElement(
                        request.elementId(),
                        request.name(),
                        imagePath,
                        request.type(),
                        request.startDate(),
                        request.endDate(),
                        request.skillSet(),
                        request.description(),
                        request.urls()
                );
        }
}
