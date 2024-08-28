package com.repo.whopper.interfaces.resume.dto;

import java.util.List;
import java.util.Set;

public class ResumeElementDto {
    public enum Status {
        ONGOING, DELETED, SUBMITTED, RELEASED
    }

    public record Writer(
            String id,
            String name,
            SchoolInfo schoolInfo,
            Major major,
            String email,
            Set<String> skillSet,
            String url
    ) {
        public Writer update(Major major, String email, Set<String> skillSet, String url) {
            return new Writer(id, name, schoolInfo, major, email, skillSet, url);
        }

        public record SchoolInfo(
                Integer grade,
                Integer classNumber,
                Integer number,
                String schoolNumber,
                Integer generation
        ) {}

        public record Major(
                String majorId,
                String majorName
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
            Set<String> skillSet,
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
