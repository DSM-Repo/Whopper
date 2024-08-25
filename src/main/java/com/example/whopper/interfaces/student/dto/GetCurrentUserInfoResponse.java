package com.example.whopper.interfaces.student.dto;

import com.example.whopper.domain.major.MajorModel;
import com.example.whopper.domain.student.ClassInfo;
import com.example.whopper.domain.student.StudentEntity;

public record GetCurrentUserInfoResponse(
        String name,
        ClassInfo classInfo,
        String profileImage,
        String majorName
) {
    public static GetCurrentUserInfoResponse fromStudentEntity(StudentEntity entity) {
        return new GetCurrentUserInfoResponse(entity.getName(), entity.getClassInfo(), entity.getProfileImagePath(), entity.getMajor().name());
    }
}
