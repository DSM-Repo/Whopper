package com.example.whopper.domain.student.domain;

public record ClassInfo(
        Integer grade,
        Integer classNumber,
        Integer number
) {
    public static ClassInfo of(Integer grade, Integer classNumber, Integer number) {
        return new ClassInfo(grade, classNumber, number);
    }

    public String getFormattedSchoolNumber() {
        return String.format("%1d%1d%02d",
                grade,
                classNumber,
                number);
    }
}
