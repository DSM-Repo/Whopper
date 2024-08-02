package com.example.whopper.domain.document.domain.element;

import com.example.whopper.domain.document.domain.element.base.AbstractElement;
import com.example.whopper.domain.document.domain.element.type.AchievementType;
import lombok.Getter;

@Getter
public class AchievementElement extends AbstractElement {
        private final String name;
        private final String institution;
        private final String date;
        private final AchievementType type;

        public AchievementElement(String elementId, String name, String institution, String date, AchievementType type) {
                super(elementId);
                this.name = name;
                this.institution = institution;
                this.date = date;
                this.type = type;
        }
}