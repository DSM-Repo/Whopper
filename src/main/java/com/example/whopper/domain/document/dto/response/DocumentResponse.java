package com.example.whopper.domain.document.dto.response;

import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.IntroduceElement;
import com.example.whopper.domain.library.domain.ShardLibrary;

import java.util.List;

public record DocumentResponse(
        IntroduceElement introduce,
        List<ShardLibrary> recentlyShared
) {
    public static DocumentResponse of(DocumentEntity document, List<ShardLibrary> recentlyShared) {
        return new DocumentResponse(
                document.getIntroduce(),
                recentlyShared
        );
    }
}
