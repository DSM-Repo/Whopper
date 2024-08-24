package com.example.whopper.domain.resume.detail;

import com.example.whopper.domain.resume.ResumeModel;

public record CompletionElementLevel(
        boolean writerInfo,
        boolean introduce,
        boolean certificateAndAward,
        boolean activity,
        boolean project,
        double percentComplete
) {
    private static final int TOTAL_ELEMENTS = 5;

    public static CompletionElementLevel of(ResumeModel resume) {
        var introduce = resume.introduce();
        var writerInfo = resume.writer();

        boolean isWriterInfoCompleted = !writerInfo.email().isBlank() &&
                !writerInfo.skillSet().isEmpty();
        boolean isIntroduceCompleted = !introduce.introduce().isBlank() && !introduce.heading().isBlank();
        boolean isCertificateAndAwardCompleted = !resume.achievementList().isEmpty();
        boolean isActivityCompleted = !resume.activityList().isEmpty();
        boolean isProjectCompleted = !resume.projectList().isEmpty();

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