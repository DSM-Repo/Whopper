package com.example.whopper.interfaces.resume.dto.response;

import com.example.whopper.domain.resume.element.DocumentStatus;
import com.example.whopper.interfaces.resume.dto.AchievementElementDto;
import com.example.whopper.interfaces.resume.dto.ActivityElementDto;
import com.example.whopper.interfaces.resume.dto.IntroduceElementDto;
import com.example.whopper.interfaces.resume.dto.ProjectElementDto;
import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.student.ClassInfo;
import com.example.whopper.domain.student.StudentEntity;

import java.time.Year;
import java.util.List;
import java.util.Set;

public record FullResumeResponse(
        String id,
        DocumentWriterResponse writer,
        DocumentStatus status,
        IntroduceElementDto introduce,
        List<ProjectElementDto>projectList,
        List<AchievementElementDto> achievementList,
        List<ActivityElementDto> activityList
) {
    public static FullResumeResponse of(DocumentEntity document, String majorName) {
        return new FullResumeResponse(
                document.getId(),
                DocumentWriterResponse.of(document.getStudent(), document, majorName),
                document.getStatus(),
                IntroduceElementDto.fromEntity(document.getIntroduce()),
                document.getProjectList().stream().map(ProjectElementDto::fromEntity).toList(),
                document.getAchievementList().stream().map(AchievementElementDto::fromEntity).toList(),
                document.getActivityList().stream().map(ActivityElementDto::fromEntity).toList()
        );
    }

    record DocumentWriterResponse(
            String name,
            String email,
            String majorName,
            ClassInfo classInfo,
            String department,
            String url,
            Set<String> skillSet
    ) {
        public static DocumentWriterResponse of(StudentEntity student, DocumentEntity document, String majorName) {
            final var classInfo = student.getClassInfo();
            final var schoolNumber = classInfo.schoolNumber();

            return new DocumentWriterResponse(
                    student.getName(),
                    document.getWriter().getEmail(),
                    majorName,
                    classInfo,
                    SchoolDepartmentEnum.getBySchoolNumber(schoolNumber),
                    document.getWriter().getUrl(),
                    document.getWriter().getSkillSet()
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
