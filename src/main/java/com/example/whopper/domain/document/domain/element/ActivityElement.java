package com.example.whopper.domain.document.domain.element;

import org.bson.types.ObjectId;

public record ActivityElement(
        String elementId,
        String name,
        String date,
        String endDate,
        boolean isPeriod,
        String description
) {
        public ActivityElement {
                if (elementId == null) {
                        elementId = new ObjectId().toHexString();
                }
        }
}
