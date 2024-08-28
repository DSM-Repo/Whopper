package com.repo.whopper.interfaces.student.dto;

import com.repo.whopper.domain.student.StudentModel;

public record GetCurrentUserInfoResponse(
        String name,
        StudentElementDto.ClassInfo classInfo,
        String profileImage,
        String majorName
) {
    public static GetCurrentUserInfoResponse fromStudentEntity(StudentModel model) {
        return new GetCurrentUserInfoResponse(model.name(), model.classInfo(), model.profileImagePath(), model.major().name());
    }
}
