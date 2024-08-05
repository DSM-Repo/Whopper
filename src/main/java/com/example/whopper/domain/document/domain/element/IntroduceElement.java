package com.example.whopper.domain.document.domain.element;

import com.example.whopper.domain.document.domain.element.base.AbstractElement;
import lombok.Getter;

@Getter
public class IntroduceElement extends AbstractElement {
    private final String heading;
    private final String introduce;

    public IntroduceElement(String elementId, String heading, String introduce) {
        super(elementId);
        this.heading = heading;
        this.introduce = introduce;
    }

    public static IntroduceElement createEmptyElement() {
        return new IntroduceElement(null, "", "");
    }

    @Override
    public String getName() {
        return "자기 소개";
    }
}