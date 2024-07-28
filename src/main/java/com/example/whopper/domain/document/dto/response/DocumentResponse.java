package com.example.whopper.domain.document.dto.response;

import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.detail.CompletionElementLevel;
import com.example.whopper.domain.document.domain.element.IntroduceElement;
import com.example.whopper.domain.document.domain.ShardDocument;
import com.example.whopper.domain.student.domain.ClassInfo;
import com.example.whopper.domain.student.domain.StudentEntity;

import java.util.List;

public record DocumentResponse(
        IntroduceElement introduce,
        CompletionElementLevel levelOfCompletion,
        List<ShardDocument> recentlyShared
) {
    public static DocumentResponse of(StudentEntity student, DocumentEntity document, List<ShardDocument> recentlyShared) {
        return new DocumentResponse(
                document.getIntroduce(),
                CompletionElementLevel.of(document),
                recentlyShared
        );
    }
}
