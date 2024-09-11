package com.repo.whopper.interfaces.student.dto;

public class StudentElementDto {
    public record ClassInfo(
            Integer grade,
            Integer classNumber,
            Integer number,
            String schoolNumber
    ) {
    }
}