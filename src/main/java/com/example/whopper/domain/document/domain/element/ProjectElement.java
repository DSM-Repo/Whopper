package com.example.whopper.domain.document.domain.element;

import com.example.whopper.domain.document.domain.element.base.AbstractElement;
import com.example.whopper.domain.document.domain.element.type.ProjectType;
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
}

