package com.example.whopper.domain.document.dto.response;

import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.*;
import com.example.whopper.domain.student.domain.StudentEntity;
import lombok.Getter;

import java.time.Year;
import java.util.List;
import java.util.Set;

public record FullDocumentResponse(
        String id,
        DocumentWriterResponse writer,
        DocumentStatus status,
        IntroduceElement introduce,
        Set<String> skillSet,
        Set<String> links,
        List<ProjectElement>projectList,
        List<AwardElement> awardList,
        List<CertificateElement> certificateList,
        List<ActivityElement> activityList
) {
    public static FullDocumentResponse of(StudentEntity student, DocumentEntity document) {
        return new FullDocumentResponse(
                document.getId(),
                DocumentWriterResponse.of(student, document),
                document.getStatus(),
                document.getIntroduce(),
                document.getWriter().skillSet(),
                document.getWriter().url(),
                document.getProjectList(),
                document.getAwardList(),
                document.getCertificateList(),
                document.getActivityList()
        );
    }

    record DocumentWriterResponse(
            String name,
            String email,
            String major,
            String schoolNumber,
            String department,
            String profileImage
    ) {
        public static DocumentWriterResponse of(StudentEntity student, DocumentEntity document) {
            final var schoolNumber = student.getClassInfo().getFormattedSchoolNumber();

            return new DocumentWriterResponse(
                    student.getName(),
                    document.getWriter().email(),
                    student.getMajor(),
                    schoolNumber,
                    SchoolDepartmentEnum.getBySchoolNumber(schoolNumber),
                    student.getProfileImagePath()
            );
        }

        @Getter
        enum SchoolDepartmentEnum {
            SW("소프트웨어개발과"),
            EM("임베디드소프트웨어과"),
            AI("인공지능소프트웨어과"),
            SE("정보보안과"),
            DE("보통교육과정"); // 1학년

            private final String department;

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
