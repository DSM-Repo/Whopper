package com.example.whopper.domain.library.application.model;

import com.example.whopper.domain.library.domain.DocumentIndex;
import com.example.whopper.domain.library.domain.type.AccessRight;

import java.time.LocalDateTime;
import java.util.List;

public record Library(
        String id,
        Integer year,
        Integer grade,
        LocalDateTime createAt,
        AccessRight accessRight,
        List<DocumentIndex> books,
        String pdfFileUrl
) {

    public Integer getGeneration() {
        return this.year - this.grade - 2013;
    }

}
