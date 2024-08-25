package com.example.whopper.domain.resume.element;

import com.example.whopper.domain.resume.element.base.AbstractElement;
import com.example.whopper.interfaces.resume.dto.IntroduceElementDto;
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

    public static IntroduceElement fromRequest(IntroduceElementDto request) {
        return new IntroduceElement(
                request.elementId(),
                request.heading(),
                request.introduce()
        );
    }

    @Override
    public String getName() {
        return "자기 소개";
    }
}