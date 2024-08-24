package com.example.whopper.interfaces.resume.dto.response;

import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.resume.element.IntroduceElement;
import com.example.whopper.domain.library.ShardLibrary;

import java.util.List;

public record ResumeResponse(
        IntroduceElementDto introduce,
        List<ShardLibrary> recentlyShared
) {
    public static ResumeResponse of(DocumentEntity document, List<ShardLibrary> recentlyShared) {
        return new ResumeResponse(
                IntroduceElementDto.fromElement(document.getIntroduce()),
                recentlyShared
        );
    }

    record IntroduceElementDto(String heading, String introduce) {
        public static IntroduceElementDto fromElement(IntroduceElement element) {
            return new IntroduceElementDto(element.getHeading(), element.getIntroduce());
        }
    }
}
