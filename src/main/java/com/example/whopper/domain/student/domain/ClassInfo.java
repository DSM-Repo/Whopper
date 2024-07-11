package com.example.whopper.domain.student.domain;

public record ClassInfo(
        Integer grade,
        Integer classNumber,
        Integer number
) {
    public static ClassInfo of(Integer grade, Integer classNumber, Integer number) {
        return new ClassInfo(grade, classNumber, number);
    }
}
