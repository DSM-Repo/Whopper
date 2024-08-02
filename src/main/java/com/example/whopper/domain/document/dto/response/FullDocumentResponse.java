package com.example.whopper.domain.document.dto.response;

import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.*;
import com.example.whopper.domain.document.domain.element.type.AchievementType;
import com.example.whopper.domain.document.domain.element.type.ProjectType;
import com.example.whopper.domain.student.domain.StudentEntity;

import java.time.Year;
import java.util.List;
import java.util.Set;

public record FullDocumentResponse(
        String id,
        DocumentWriterResponse writer,
        DocumentStatus status,
        IntroduceElementResponse introduce,
        Set<String> skillSet,
        Set<String> links,
        List<ProjectElementResponse>projectList,
        List<AchievementElementResponse> awardList,
        List<ActivityElementResponse> activityList
) {
    public static FullDocumentResponse of(DocumentEntity document, StudentEntity student, String majorName) {
        return new FullDocumentResponse(
                document.getId(),
                DocumentWriterResponse.of(student, document, majorName),
                document.getStatus(),
                IntroduceElementResponse.of(document.getIntroduce()),
                document.getWriter().getSkillSet(),
                document.getWriter().getUrl(),
                document.getProjectList().stream().map(ProjectElementResponse::of).toList(),
                document.getAchievementList().stream().map(AchievementElementResponse::of).toList(),
                document.getActivityList().stream().map(ActivityElementResponse::of).toList()
        );
    }

    record IntroduceElementResponse(
            String elementId,
            String heading,
            String introduce,
            List<String> feedback
    ) {
        public static IntroduceElementResponse of(IntroduceElement element) {
            return new IntroduceElementResponse(
                    element.getElementId(),
                    element.getHeading(),
                    element.getIntroduce(),
                    List.of()
            );
        }
    }

    record ProjectElementResponse(
            String elementId,
            String name,
            String imagePath,
            ProjectType type,
            String startDate,
            String endDate,
            Set<String> skillSet,
            String description,
            Set<String> urls,
            List<String> feedback
    ) {
        public static ProjectElementResponse of(ProjectElement element) {
            return new ProjectElementResponse(
                    element.getElementId(),
                    element.getName(),
                    element.getImagePath(),
                    element.getType(),
                    element.getStartDate(),
                    element.getEndDate(),
                    element.getSkillSet(),
                    element.getDescription(),
                    element.getUrls(),
                    List.of()
            );
        }
    }

    record AchievementElementResponse(
            String elementId,
            String name,
            String institution,
            String date,
            AchievementType type,
            List<String> feedback
    ) {
        public static AchievementElementResponse of(AchievementElement element) {
            return new AchievementElementResponse(
                    element.getElementId(),
                    element.getName(),
                    element.getInstitution(),
                    element.getDate(),
                    element.getType(),
                    List.of()
            );
        }
    }

    record ActivityElementResponse(
            String elementId,
            String name,
            String date,
            String endDate,
            boolean isPeriod,
            String description,
            List<String> feedback
    ) {
        public static ActivityElementResponse of(ActivityElement element) {
            return new ActivityElementResponse(
                    element.getElementId(),
                    element.getName(),
                    element.getDate(),
                    element.getEndDate(),
                    element.isPeriod(),
                    element.getDescription(),
                    List.of()
            );
        }
    }

    record DocumentWriterResponse(
            String name,
            String email,
            String majorName,
            String schoolNumber,
            String department,
            String profileImage
    ) {
        public static DocumentWriterResponse of(StudentEntity student, DocumentEntity document, String majorName) {
            final var schoolNumber = student.getClassInfo().schoolNumber();

            return new DocumentWriterResponse(
                    student.getName(),
                    document.getWriter().getEmail(),
                    majorName,
                    schoolNumber,
                    SchoolDepartmentEnum.getBySchoolNumber(schoolNumber),
                    student.getProfileImagePath()
            );
        }

        enum SchoolDepartmentEnum {
            SW("소프트웨어개발과"),
            EM("임베디드소프트웨어과"),
            AI("인공지능소프트웨어과"),
            SE("정보보안과"),
            DE("보통교육과정"); // 1학년

            private final String department;

            public String getDepartment() {
                return department;
            }

            SchoolDepartmentEnum(String department) {
                this.department = department;
            }

            public static String getBySchoolNumber(String schoolNumber) {
                if (schoolNumber == null || schoolNumber.isBlank() || schoolNumber.length() != 4) {
                    return DE.getDepartment();
                }

                final var prefix = schoolNumber.substring(0, 2);
                return getDepartmentByPrefix(prefix);
            }

            private static String getDepartmentByPrefix(String prefix) {
                final var isAfter2024 = Year.now().getValue() > 2024;

                return switch (prefix) {
                    case "21", "22", "31", "32" -> SW.getDepartment();
                    case "23", "33" -> EM.getDepartment();
                    case "24" -> AI.getDepartment();
                    case "34" -> isAfter2024 ? AI.getDepartment() : SE.getDepartment();
                    case null, default -> DE.getDepartment();
                };
            }
        }
    }
}
