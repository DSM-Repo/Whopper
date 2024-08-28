package com.example.whopper.interfaces.student.dto;

public class StudentElementDto {
    public record ClassInfo(
            Integer grade,
            Integer classNumber,
            Integer number,
            String schoolNumber
    ) {
    }

    public record Major(
            String id,
            String name
    ) {}
}