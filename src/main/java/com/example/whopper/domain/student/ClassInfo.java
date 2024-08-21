package com.example.whopper.domain.student;

import org.springframework.data.mongodb.core.mapping.Field;

public record ClassInfo(
        @Field("grade")
        Integer grade,
        @Field("classNumber")
        Integer classNumber,
        @Field("number")
        Integer number,
        @Field("schoolNumber")
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
