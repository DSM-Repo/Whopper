package com.example.whopper.lagacy.document.dto;

import com.example.whopper.lagacy.document.domain.element.IntroduceElement;

public record IntroduceElementDto(String elementId, String heading, String introduce) {
    public static IntroduceElementDto fromEntity(IntroduceElement element) {
        return new IntroduceElementDto(
                element.getElementId(),
                element.getHeading(),
                element.getIntroduce()
        );
    }
}
