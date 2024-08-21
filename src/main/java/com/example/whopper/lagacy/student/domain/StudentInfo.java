package com.example.whopper.lagacy.student.domain;

public record StudentInfo(
        String schoolNumber,
        String name,
        String profileImage
) {
    public static StudentInfo of(StudentEntity student) {
        return new StudentInfo(
                student.getClassInfo().schoolNumber(),
                student.getName(),
                student.getProfileImagePath()
        );
    }
}