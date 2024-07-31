package com.example.whopper.domain.student.domain;

public record ClassInfo(
        Integer grade,
        Integer classNumber,
        Integer number,
        String schoolNumber // 학번
) {
    public static ClassInfo of(Integer grade, Integer classNumber, Integer number) {
        return new ClassInfo(grade, classNumber, number, getFormattedSchoolNumber(grade, classNumber, number));
    }

    private static String getFormattedSchoolNumber(Integer grade, Integer classNumber, Integer number) {
        return String.format("%1d%1d%02d",
                grade,
                classNumber,
                number);
    }
}
