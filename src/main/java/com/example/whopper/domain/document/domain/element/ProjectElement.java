package com.example.whopper.domain.document.domain.element;

import com.example.whopper.domain.document.domain.element.base.AbstractElement;
import com.example.whopper.domain.document.domain.element.type.ProjectType;
import com.example.whopper.domain.document.dto.request.UpdateProjectElementRequest;
import lombok.Getter;

import java.util.Set;

@Getter
public class ProjectElement extends AbstractElement {
        private final String name;
        private final String imagePath;
        private final ProjectType type;
        private final String startDate;
        private final String endDate;
        private final Set<String> skillSet;
        private final String description;
        private final String url;

        protected ProjectElement(String elementId, String name, String imagePath, ProjectType type, String startDate, String endDate, Set<String> skillSet, String description, String url) {
                super(elementId);
                this.name = name;
                this.imagePath = imagePath;
                this.type = type;
                this.startDate = startDate;
                this.endDate = endDate;
                this.skillSet = skillSet;
                this.description = description;
                this.url = url;
        }

        public static ProjectElement fromRequest(UpdateProjectElementRequest request) {
                return new ProjectElement(
                        request.elementId(),
                        request.name(),
                        request.imagePath(),
                        request.type(),
                        request.startDate(),
                        request.endDate(),
                        request.skillSet(),
                        request.description(),
                        request.url()
                );
        }
}

