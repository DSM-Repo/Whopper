package com.example.whopper.domain.resume;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Getter
@Builder
@Document(collection = "resume_repo")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class ResumeEntity {

    /* fields */
    @Id
    private String id;
    private Integer year;
    private Status status;
    private Writer writer;

    private Introduce introduce;
    private List<Project> projectList;
    private List<Achievement> achievementList;
    private List<Activity> activityList;

    /* value objects */

    record Activity(
            String elementId,
            String name,
            Date date,
            boolean isPeriod,
            String description
    ) {}

    record Achievement(
            String elementId,
            String name,
            String institution,
            String date,
            Type type
    ) {
        enum Type {
            AWARD, CERTIFICATE
        }
    }

    record Introduce(
            String heading,
            String introduce
    ) {}

    record Project(
            String elementId,
            String name,
            Logo logo,
            Type type,
            Date date,
            Set<String> skillSet,
            List<Section> sections,
            String url
    ) {
        record Section(
                String elementId,
                String title,
                String description
        ) {}

        record Logo(
                String imagePath,
                String originalName
        ) {}

        enum Type {
            TEAM, PERSONAL
        }
    }

    record Writer(
            String id,
            String name,
            SchoolInfo schoolInfo,
            Major major,
            String email,
            Set<String> skillSet,
            String url
    ) {
        record SchoolInfo(
                Integer grade,
                Integer classNumber,
                String schoolNumber,
                Integer generation
        ) {}

        record Major(
                String majorId,
                String majorName
        ) {}
    }

    /**
     * 현재 레주메의 상태를 표현합니다.
     *
     * <ul>
     *   <li>{@link #ONGOING} - 레주메가 현재 작성 중입니다.</li>
     *   <li>{@link #DELETED} - 레주메가 삭제되었습니다.</li>
     *   <li>{@link #SUBMITTED} - 레주메가 제출되어, 선생님이 피드백을 작성할 수 있습니다.</li>
     *   <li>{@link #RELEASED} - 레주메가 공개되어, 선생님이 PDF화를 할 수 있습니다.</li>
     * </ul>
     */
    enum Status {
        ONGOING, DELETED, SUBMITTED, RELEASED
    }

    record Date(
            String startDate,
            String endDate
    ) {}

    protected ResumeEntity() {}
}
