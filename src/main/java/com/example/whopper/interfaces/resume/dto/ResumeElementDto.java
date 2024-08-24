package com.example.whopper.interfaces.resume.dto;

import java.util.List;
import java.util.Set;

public class ResumeElementDto {

    public enum Status {
        ONGOING, DELETED, SUBMITTED, RELEASED
    }

    public record Writer(
            String name,
            SchoolInfo schoolInfo,
            Major major,
            String email,
            Set<String> skillSet,
            String url
    ) {
        public Writer update(Major major, String email, Set<String> skillSet, String url) {
            return new Writer(name, schoolInfo, major, email, skillSet, url);
        }

        public record SchoolInfo(
                Integer grade,
                Integer classNumber,
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
            String name,
            Logo projectLogo,
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
