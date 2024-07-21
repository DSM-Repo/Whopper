package com.example.whopper.domain.document.domain.element;

public record IntroduceElement(
        String heading,
        String introduce
) {
    public static IntroduceElement createEmptyElement() {
        return new IntroduceElement("", "");
    }
}