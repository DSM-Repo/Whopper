package com.dsm.repo.internal.core.domain.model.student;

import com.dsm.repo.external.web.rest.resume.dto.ResumeElementDto;

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