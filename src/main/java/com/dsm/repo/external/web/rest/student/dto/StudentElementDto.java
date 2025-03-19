package com.dsm.repo.external.web.rest.student.dto;

public class StudentElementDto {
    public record ClassInfo(
            Integer grade,
            Integer classNumber,
            Integer number,
            String schoolNumber
    ) {
        public ClassInfo(Integer grade, Integer classNumber, Integer number) {
            this(grade, classNumber, number, String.format("%1d%1d%02d", grade, classNumber, number));
        }
    }
}