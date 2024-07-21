package com.example.whopper.domain.document.domain.detail;

import com.example.whopper.domain.document.domain.DocumentEntity;

public record CompletionElementLevel(
        boolean writerInfo,
        boolean introduce,
        boolean certificate,
        boolean award,
        boolean activity,
        boolean project
) {
    public static CompletionElementLevel of(DocumentEntity document) {
        var introduce = document.getIntroduce();
        var writerInfo = document.getWriter();

        return new CompletionElementLevel(
                (!writerInfo.email().isBlank() && !writerInfo.major().isBlank() &&
                !writerInfo.skillSet().isEmpty() && writerInfo.generation() != null),
                (!introduce.introduce().isBlank() && !introduce.heading().isBlank()),
                !document.getCertificateList().isEmpty(),
                !document.getAwardList().isEmpty(),
                !document.getActivityList().isEmpty(),
                !document.getProjectList().isEmpty()
        );
    }
}
