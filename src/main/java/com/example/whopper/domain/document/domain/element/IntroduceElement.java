package com.example.whopper.domain.document.domain.element;

public record IntroduceElement(
        String heading,
        String introduce
) {
    public IntroduceElement(String heading, String introduce) {
        this.heading = heading.isEmpty() ? "" : heading;
        this.introduce = introduce.isEmpty() ? "" : introduce;
    }
}