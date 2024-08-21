package com.example.whopper.interfaces.resume.dto;

import com.example.whopper.domain.resume.element.IntroduceElement;

public record IntroduceElementDto(String elementId, String heading, String introduce) {
    public static IntroduceElementDto fromEntity(IntroduceElement element) {
        return new IntroduceElementDto(
                element.getElementId(),
                element.getHeading(),
                element.getIntroduce()
        );
    }
}
