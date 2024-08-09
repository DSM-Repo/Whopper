package com.example.whopper.domain.document.domain.element;

import com.example.whopper.domain.document.domain.element.base.AbstractElement;
import com.example.whopper.domain.document.dto.request.UpdateActivityElementRequest;
import lombok.Getter;

@Getter
public class ActivityElement extends AbstractElement {
        private final String name;
        private final String date;
        private final String endDate;
        private final boolean isPeriod;
        private final String description;

        protected ActivityElement(String elementId, String name, String date, String endDate, boolean isPeriod, String description) {
                super(elementId);
                this.name = name;
                this.date = date;
                this.endDate = endDate;
                this.isPeriod = isPeriod;
                this.description = description;
        }

        public static ActivityElement fromRequest(UpdateActivityElementRequest request) {
                return new ActivityElement(
                        request.elementId(),
                        request.name(),
                        request.date(),
                        request.endDate(),
                        request.isPeriod(),
                        request.description()
                );
        }
}