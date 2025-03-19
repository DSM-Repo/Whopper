package com.dsm.repo.internal.core.domain.model.resume;

import com.dsm.repo.external.web.rest.resume.dto.ResumeElementDto;
import lombok.Builder;

import java.time.Year;
import java.util.Collections;
import java.util.List;

@Builder
public record ResumeModel(
        String id,
        Integer year,
        ResumeElementDto.Status status,
        ResumeElementDto.Writer writer,
        ResumeElementDto.Introduce introduce,
        List<ResumeElementDto.Project> projectList,
        List<ResumeElementDto.Achievement> achievementList,
        List<ResumeElementDto.Activity> activityList
) {
    public static ResumeModel createInitialResume(String id, String name, ResumeElementDto.Writer.SchoolInfo schoolInfo, String major) {
        return new ResumeModel(
                null,
                Year.now().getValue(),
                ResumeElementDto.Status.ONGOING,
                new ResumeElementDto.Writer(id, name, schoolInfo, major, "", Collections.emptyList(), ""),
                new ResumeElementDto.Introduce("", ""),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }

    public ResumeModel updateYear() {
        return new ResumeModel(id, Year.now().getValue(), status, writer, introduce, projectList, achievementList, activityList);
    }

    public ResumeModel release() {
        return new ResumeModel(id, year, ResumeElementDto.Status.RELEASED, writer, introduce, projectList, achievementList, activityList);
    }

    public ResumeModel submit() {
        return new ResumeModel(id, year, ResumeElementDto.Status.SUBMITTED, writer, introduce, projectList, achievementList, activityList);
    }

    public ResumeModel onGoing() {
        return new ResumeModel(id, year, ResumeElementDto.Status.ONGOING, writer, introduce, projectList, achievementList, activityList);
    }

    public ResumeModel delete() {
        return new ResumeModel(id, year, ResumeElementDto.Status.DELETED, writer, introduce, projectList, achievementList, activityList);
    }

    public ResumeModel replace(ResumeElementDto.Writer newWriter, List<ResumeElementDto.Project> newProjectList, ResumeElementDto.Introduce newIntroduce, List<ResumeElementDto.Achievement> newAchievementList, List<ResumeElementDto.Activity> newActivityList) {
        return new ResumeModel(id, year, status, newWriter, newIntroduce, newProjectList, newAchievementList, newActivityList);
    }
    public ResumeModel updateWriterInfo(ResumeElementDto.Writer newWriter) {
        return new ResumeModel(id, year, status, newWriter, introduce, projectList, achievementList, activityList);
    }

    public ResumeModel updateProjectList(List<ResumeElementDto.Project> newProjectList) {
        return new ResumeModel(id, year, status, writer, introduce, newProjectList, achievementList, activityList);
    }

    public ResumeModel updateIntroduceElement(ResumeElementDto.Introduce newIntroduce) {
        return new ResumeModel(id, year, status, writer, newIntroduce, projectList, achievementList, activityList);
    }

    public ResumeModel updateAchievementList(List<ResumeElementDto.Achievement> newAchievementList) {
        return new ResumeModel(id, year, status, writer, introduce, projectList, newAchievementList, activityList);
    }

    public ResumeModel updateActivityList(List<ResumeElementDto.Activity> newActivityList) {
        return new ResumeModel(id, year, status, writer, introduce, projectList, achievementList, newActivityList);
    }
}