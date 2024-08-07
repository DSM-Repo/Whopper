package com.example.whopper.domain.document.domain.detail;

import com.example.whopper.domain.document.domain.DocumentEntity;

public record CompletionElementLevel(
        boolean writerInfo,
        boolean introduce,
        boolean certificateAndAward,
        boolean activity,
        boolean project,
        double percentComplete
) {
    private static final int TOTAL_ELEMENTS = 5;

    public static CompletionElementLevel of(DocumentEntity document) {
        var introduce = document.getIntroduce();
        var writerInfo = document.getWriter();

        boolean isWriterInfoCompleted = !writerInfo.getEmail().isBlank() &&
                !writerInfo.getSkillSet().isEmpty() && writerInfo.getGeneration() != null;
        boolean isIntroduceCompleted = !introduce.getIntroduce().isBlank() && !introduce.getHeading().isBlank();
        boolean isCertificateAndAwardCompleted = !document.getAchievementList().isEmpty();
        boolean isActivityCompleted = !document.getActivityList().isEmpty();
        boolean isProjectCompleted = !document.getProjectList().isEmpty();

        double completedElementsCount = (double)
                (isWriterInfoCompleted ? 1 : 0) +
                (isIntroduceCompleted ? 1 : 0) +
                (isCertificateAndAwardCompleted ? 1 : 0) +
                (isActivityCompleted ? 1 : 0) +
                (isProjectCompleted ? 1 : 0);
    
        final double percentComplete = (completedElementsCount / TOTAL_ELEMENTS) * 100;

        return new CompletionElementLevel(
                isWriterInfoCompleted,
                isIntroduceCompleted,
                isCertificateAndAwardCompleted,
                isActivityCompleted,
                isProjectCompleted,
                percentComplete
        );
    }
}