package com.example.whopper.lagacy.document.dto.response;

import com.example.whopper.lagacy.document.domain.DocumentEntity;
import com.example.whopper.lagacy.document.domain.element.IntroduceElement;
import com.example.whopper.lagacy.library.domain.ShardLibrary;

import java.util.List;

public record DocumentResponse(
        IntroduceElementDto introduce,
        List<ShardLibrary> recentlyShared
) {
    public static DocumentResponse of(DocumentEntity document, List<ShardLibrary> recentlyShared) {
        return new DocumentResponse(
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
