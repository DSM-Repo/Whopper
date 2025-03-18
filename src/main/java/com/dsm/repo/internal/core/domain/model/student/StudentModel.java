package com.dsm.repo.internal.core.domain.model.student;

import com.dsm.repo.external.web.rest.student.dto.StudentElementDto;
import lombok.Builder;

@Builder
public record StudentModel(
        String id,
        String accountId,
        String password,
        String name,
        StudentElementDto.ClassInfo classInfo,
        String profileImagePath,
        String major
) {
    public StudentModel updateMajor(String majorName) {
        return new StudentModel(id, accountId, password, name, classInfo, profileImagePath, majorName);
    }

    public StudentModel(String accountId, String password, String name, Integer grade, Integer classNumber, Integer number, String profileImagePath, String majorName) {
        this(null,
                accountId,
                password,
                name,
                new StudentElementDto.ClassInfo(
                        grade,
                        classNumber,
                        number,
                        String.format("%1d%1d%02d", grade, classNumber, number)
                ),
                profileImagePath,
                majorName
        );
    }
}
