package com.example.whopper.interfaces.library.dto.response;

import com.example.whopper.domain.library.LibraryModel;
import com.example.whopper.interfaces.library.dto.LibraryElementDto;

import java.util.List;

public record LibraryDetailResponse(
        String id,
        Integer year,
        Integer grade,
        Integer generation,
        String resumeUrl,
        List<LibraryElementDto.ResumeIndex> index
) {
    public LibraryDetailResponse(LibraryModel library, String resumeUrl) {
        this(library.id(), library.year(), library.grade(), library.getGeneration(), resumeUrl, library.index());
    }
}