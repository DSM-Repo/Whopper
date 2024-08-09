package com.example.whopper.domain.student.dto;

import com.example.whopper.domain.major.domain.MajorEntity;
import com.example.whopper.domain.student.domain.ClassInfo;
import com.example.whopper.domain.student.domain.StudentEntity;

public record GetCurrentUserInfoResponse(
        String name,
        ClassInfo classInfo,
        String profileImage,
        String majorName
) {
    public static GetCurrentUserInfoResponse fromStudentEntity(StudentEntity entity, MajorEntity major) {
        var majorName = major == null ? "전공 미정" : major.getName();
        return new GetCurrentUserInfoResponse(entity.getName(), entity.getClassInfo(), entity.getProfileImagePath(), majorName);
    }
}
