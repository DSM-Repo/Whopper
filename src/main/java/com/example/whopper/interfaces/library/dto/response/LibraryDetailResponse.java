package com.example.whopper.interfaces.library.dto.response;

import com.example.whopper.domain.library.LibraryEntity;

import java.util.List;

public record LibraryDetailResponse(
        String id,
        Integer year,
        Integer grade,
        Integer generation,
        String resumeUrl,
        List<ResumeIndex> index
) {
    public LibraryDetailResponse(LibraryEntity library, String resumeUrl) {
        this(library.getId(), library.getYear(), library.getGrade(), library.getGeneration(), resumeUrl, library.getIndex());
    }
}