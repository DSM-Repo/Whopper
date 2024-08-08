package com.example.whopper.domain.document.domain.element;

import com.example.whopper.domain.document.domain.element.base.AbstractElement;
import lombok.Getter;

@Getter
public class ActivityElement extends AbstractElement {
        private final String name;
        private final String date;
        private final String endDate;
        private final boolean isPeriod;
        private final String description;

        public ActivityElement(String elementId, String name, String date, String endDate, String description) {
                super(elementId);
                this.name = name;
                this.date = date;
                this.endDate = endDate;
                this.isPeriod = endDate == null;
                this.description = description;
        }
}