package com.repo.whopper.interfaces.student.dto;

import com.repo.whopper.domain.student.StudentModel;

public record CurrentUserInfoResponse(
        String name,
        StudentElementDto.ClassInfo classInfo,
        String profileImage,
        String majorName
) {
    public static CurrentUserInfoResponse fromStudentModel(StudentModel model) {
        return new CurrentUserInfoResponse(model.name(), model.classInfo(), model.profileImagePath(), model.major().name());
    }
}
