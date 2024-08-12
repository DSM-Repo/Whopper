package com.example.whopper.domain.document.domain.element;

import com.example.whopper.domain.document.domain.element.base.AbstractElement;
import com.example.whopper.domain.document.domain.element.type.ProjectType;
import com.example.whopper.domain.document.dto.ProjectElementDto;
import com.example.whopper.domain.file.domain.ImageInfo;
import lombok.Getter;

import java.util.Set;

@Getter
public class ProjectElement extends AbstractElement {
        private final String name;
        private final ImageInfo imageInfo;
        private final ProjectType type;
        private final String startDate;
        private final String endDate;
        private final Set<String> skillSet;
        private final ProjectDescription description;
        private final String url;

        protected ProjectElement(String elementId, String name, ImageInfo imageInfo, ProjectType type, String startDate, String endDate, Set<String> skillSet, ProjectDescription description, String url) {
                super(elementId);
                this.name = name;
                this.imageInfo = imageInfo;
                this.type = type;
                this.startDate = startDate;
                this.endDate = endDate;
                this.skillSet = skillSet;
                this.description = description;
                this.url = url;
        }

        public static ProjectElement fromRequest(ProjectElementDto request) {
                return new ProjectElement(
                        request.elementId(),
                        request.name(),
                        request.imageInfo(),
                        request.type(),
                        request.startDate(),
                        request.endDate(),
                        request.skillSet(),
                        request.description(),
                        request.url()
                );
        }

        public record ProjectDescription(
                String motive,
                String role,
                String retrospective
        ) {
                public static ProjectDescription of(String motive, String role, String retrospective) {
                        return new ProjectDescription(motive, role, retrospective);
                }
        }
}
