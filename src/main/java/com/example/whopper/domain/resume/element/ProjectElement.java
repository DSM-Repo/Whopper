package com.example.whopper.domain.resume.element;

import com.example.whopper.domain.resume.element.base.AbstractElement;
import com.example.whopper.domain.resume.element.type.ProjectType;
import com.example.whopper.interfaces.resume.dto.ProjectElementDto;
import com.example.whopper.domain.file.ImageInfo;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class ProjectElement extends AbstractElement {
        private final String name;
        private final ImageInfo imageInfo;
        private final ProjectType type;
        private final String startDate;
        private final String endDate;
        private final Set<String> skillSet;
        private final List<Section> sections;
        private final String url;

        protected ProjectElement(String elementId, String name, ImageInfo imageInfo, ProjectType type, String startDate, String endDate, Set<String> skillSet, List<Section> sections, String url) {
                super(elementId);
                this.name = name;
                this.imageInfo = imageInfo;
                this.type = type;
                this.startDate = startDate;
                this.endDate = endDate;
                this.skillSet = skillSet;
                this.sections = sections;
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
                        request.sections(),
                        request.url()
                );
        }

        public record Section(
                String title,
                String description
        ) {
                public static Section of(String title, String description) {
                        return new Section(title, description);
                }
        }
}
