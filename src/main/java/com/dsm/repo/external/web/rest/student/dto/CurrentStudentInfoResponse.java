package com.dsm.repo.external.web.rest.student.dto;

import com.dsm.repo.internal.core.domain.model.student.StudentModel;

public record CurrentStudentInfoResponse(
        String name,
        StudentElementDto.ClassInfo classInfo,
        String profileImage,
        String major
) {
    public static CurrentStudentInfoResponse fromStudentModel(StudentModel model) {
        return new CurrentStudentInfoResponse(model.name(), model.classInfo(), model.profileImagePath(), model.major());
    }
}
