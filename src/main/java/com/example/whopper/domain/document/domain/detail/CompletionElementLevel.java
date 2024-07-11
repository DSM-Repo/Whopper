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
        return new CompletionElementLevel(
                document.getWriter() != null,
                document.getIntroduce() != null,
                !document.getCertificateList().isEmpty(),
                !document.getAwardList().isEmpty(),
                !document.getActivityList().isEmpty(),
                !document.getProjectList().isEmpty()
        );
    }
}
