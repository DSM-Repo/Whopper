package com.example.whopper.domain.student.domain;

public record PrivateStudentInfo(
        String name,
        Integer grade,
        Integer classNumber,
        String majorId,
        String schoolNumber
) {
    public static PrivateStudentInfo of(String name, Integer grade, Integer classNumber, String majorId, String schoolNumber) {
        return new PrivateStudentInfo(name, grade, classNumber, majorId, schoolNumber);
    }
}
