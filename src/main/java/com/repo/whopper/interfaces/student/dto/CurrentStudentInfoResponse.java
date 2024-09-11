package com.repo.whopper.interfaces.student.dto;

import com.repo.whopper.domain.student.StudentModel;

public record CurrentStudentInfoResponse(
        String name,
        StudentElementDto.ClassInfo classInfo,
        String profileImage,
        String major
) {
    public static CurrentStudentInfoResponse fromStudentModel(StudentModel model) {
        return new CurrentStudentInfoResponse(model.name(), model.classInfo(), model.profileImagePath(), model.major().name());
    }
}
