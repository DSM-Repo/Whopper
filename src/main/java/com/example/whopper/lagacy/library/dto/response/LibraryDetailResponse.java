package com.example.whopper.lagacy.library.dto.response;

import com.example.whopper.lagacy.library.domain.DocumentIndex;
import com.example.whopper.lagacy.library.domain.LibraryEntity;

import java.util.List;

public record LibraryDetailResponse(
        String id,
        Integer year,
        Integer grade,
        Integer generation,
        String documentUrl,
        List<DocumentIndex> index
) {
    public LibraryDetailResponse(LibraryEntity library, String documentUrl) {
        this(library.getId(), library.getYear(), library.getGrade(), library.getGeneration(), documentUrl, library.getIndex());
    }
}