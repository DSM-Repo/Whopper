package com.example.whopper.domain.resume.element;

import com.example.whopper.domain.resume.element.base.AbstractElement;
import com.example.whopper.interfaces.resume.dto.ActivityElementDto;
import lombok.Getter;

@Getter
public class ActivityElement extends AbstractElement {
        private final String name;
        private final String startDate;
        private final String endDate;
        private final boolean isPeriod;
        private final String description;

        protected ActivityElement(String elementId, String name, String startDate, String endDate, boolean isPeriod, String description) {
                super(elementId);
                this.name = name;
                this.startDate = startDate;
                this.endDate = endDate;
                this.isPeriod = isPeriod;
                this.description = description;
        }

        public static ActivityElement fromRequest(ActivityElementDto request) {
                return new ActivityElement(
                        request.elementId(),
                        request.name(),
                        request.startDate(),
                        request.endDate(),
                        request.isPeriod(),
                        request.description()
                );
        }
}