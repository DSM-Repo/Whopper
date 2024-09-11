package com.repo.whopper.interfaces.resume.dto;

import com.repo.whopper.interfaces.resume.dto.request.UpdateWriterInfoRequest;

import java.util.List;

public class ResumeElementDto {

    public record ReviseRequest(
            UpdateWriterInfoRequest writer,
            Introduce introduce,
            List<Project> projectList,
            List<Achievement> achievementList,
            List<Activity> activityList
    ) {
    }

    public enum Status {
        ONGOING, DELETED, SUBMITTED, RELEASED
    }

    public record Writer(
            String id,
            String name,
            SchoolInfo schoolInfo,
            String major,
            String email,
            List<String> skillSet,
            String url
    ) {
        public Writer update(String major, String email, List<String> skillSet, String url) {
            return new Writer(id, name, schoolInfo, major, email, skillSet, url);
        }

        public record SchoolInfo(
                Integer grade,
                Integer classNumber,
                Integer number,
                String schoolNumber,
                Integer generation
        ) {}
    }

    public record Introduce(
            String heading,
            String introduce
    ) {}

    public record Project(
            String elementId,
            String name,
            Logo logo,
            Type type,
            Date date,
            List<String> skillSet,
            List<Section> sections,
            String url
    ) {

        public enum Type {
            TEAM, PERSONAL
        }

        public record Section(
                String elementId,
                String title,
                String description
        ) {
        }

        public record Logo(
                String imagePath,
                String originalName
        ) {
        }
    }

    public record Achievement(
            String elementId,
            String name,
            String institution,
            String date,
            Type type
    ) {
        public enum Type {
            AWARD, CERTIFICATE
        }
    }

    public record Activity(
            String elementId,
            String name,
            Date date,
            boolean isPeriod,
            String description
    ) {}

    public record Date(
            String startDate,
            String endDate
    ) {}
}
