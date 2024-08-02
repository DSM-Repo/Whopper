package com.example.whopper.domain.document.domain.element;

import org.bson.types.ObjectId;

public record IntroduceElement(
        String elementId,
        String heading,
        String introduce
) {
    public IntroduceElement {
        if (elementId == null) {
            elementId = new ObjectId().toHexString();
        }
    }

    public static IntroduceElement createEmptyElement() {
        return new IntroduceElement(null, "", "");
    }
}