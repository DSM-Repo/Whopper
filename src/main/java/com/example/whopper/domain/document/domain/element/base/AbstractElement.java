package com.example.whopper.domain.document.domain.element.base;

import lombok.Getter;
import org.bson.types.ObjectId;

@Getter
public abstract class AbstractElement implements NamedElement {
    protected final String elementId;

    protected AbstractElement(String elementId) {
        this.elementId = (elementId == null || elementId.isBlank()) ? new ObjectId().toHexString() : elementId;
    }
}
