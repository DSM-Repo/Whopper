package com.example.whopper.domain.resume.element;

import com.example.whopper.domain.resume.element.base.AbstractElement;
import com.example.whopper.domain.resume.element.type.AchievementType;
import com.example.whopper.interfaces.resume.dto.AchievementElementDto;
import lombok.Getter;

@Getter
public class AchievementElement extends AbstractElement {
        private final String name;
        private final String institution;
        private final String date;
        private final AchievementType type;

        protected AchievementElement(String elementId, String name, String institution, String date, AchievementType type) {
                super(elementId);
                this.name = name;
                this.institution = institution;
                this.date = date;
                this.type = type;
        }

        public static AchievementElement fromRequest(AchievementElementDto request) {
                return new AchievementElement(
                        request.elementId(),
                        request.name(),
                        request.institution(),
                        request.date(),
                        request.type()
                );
        }
}