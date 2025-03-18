package com.dsm.repo.external.web.rest.student.dto;

public class StudentElementDto {
    public record ClassInfo(
            Integer grade,
            Integer classNumber,
            Integer number,
            String schoolNumber
    ) {
    }
}