package com.example.whopper.domain.resume;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Getter(value = AccessLevel.PACKAGE)
@Builder(access = AccessLevel.PACKAGE)
@Document(collection = "document_repo")
class DocumentEntity {

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
            String name,
            Date date,
            boolean isPeriod,
            String description
    ) {}

    record Achievement(
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
            String name,
            Image projectLogo,
            Type type,
            Date date,
            Set<String> skillSet,
            List<Section> sections,
            String url
    ) {
        record Section(
                String title,
                String description
        ) {}

        record Image(
                String imagePath,
                String originalName
        ) {}

        enum Type {
            TEAM, PERSONAL
        }
    }

    record Writer(
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

    protected DocumentEntity() {}
}
