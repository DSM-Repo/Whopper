package com.repo.whopper.interfaces.resume.dto.response;

import com.repo.whopper.domain.resume.ResumeModel;
import com.repo.whopper.interfaces.resume.dto.*;

import java.time.Year;
import java.util.List;
import java.util.Set;

public record FullResumeResponse(
        String id,
        ResumeWriterResponse writer,
        ResumeElementDto.Status status,
        ResumeElementDto.Introduce introduce,
        List<ResumeElementDto.Project>projectList,
        List<ResumeElementDto.Achievement> achievementList,
        List<ResumeElementDto.Activity> activityList
) {
    public static FullResumeResponse of(ResumeModel resume) {
        return new FullResumeResponse(
                resume.id(),
                ResumeWriterResponse.of(resume),
                resume.status(),
                resume.introduce(),
                resume.projectList(),
                resume.achievementList(),
                resume.activityList()
        );
    }

    record ResumeWriterResponse(
            String name,
            String email,
            String majorName,
            ResumeElementDto.Writer.SchoolInfo classInfo,
            String department,
            String url,
            List<String> skillSet
    ) {
        public static ResumeWriterResponse of(ResumeModel resume) {
            final var schoolInfo = resume.writer().schoolInfo();
            final var schoolNumber = schoolInfo.schoolNumber();
            final var writer = resume.writer();

            return new ResumeWriterResponse(
                    writer.name(),
                    writer.email(),
                    writer.major(),
                    schoolInfo,
                    SchoolDepartmentEnum.getBySchoolNumber(schoolNumber),
                    writer.url(),
                    writer.skillSet()
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
