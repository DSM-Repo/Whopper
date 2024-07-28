package com.example.whopper.domain.document.domain.element;

import com.example.whopper.domain.document.domain.element.type.AchievementType;
import org.bson.types.ObjectId;

public record AchievementElement(
        String elementId,
        String name,
        String institution,
        String date,
        AchievementType type
) {
        public AchievementElement {
                if (elementId == null) {
                        elementId = new ObjectId().toHexString();
                }
        }
}
