package com.repo.whopper.domain.student;

import com.repo.whopper.interfaces.resume.dto.ResumeElementDto;

public record StudentInfo(
        String schoolNumber,
        String name
) {
    public static StudentInfo of(ResumeElementDto.Writer writer) {
        return new StudentInfo(
                writer.schoolInfo().schoolNumber(),
                writer.name()
        );
    }
}