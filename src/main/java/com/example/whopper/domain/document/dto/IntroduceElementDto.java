package com.example.whopper.domain.document.dto;

import com.example.whopper.domain.document.domain.element.IntroduceElement;

public record IntroduceElementDto(String elementId, String heading, String introduce) {
    public static IntroduceElementDto fromEntity(IntroduceElement element) {
        return new IntroduceElementDto(
                element.getElementId(),
                element.getHeading(),
                element.getIntroduce()
        );
    }
}
