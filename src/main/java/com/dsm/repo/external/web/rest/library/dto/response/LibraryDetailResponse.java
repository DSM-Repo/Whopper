package com.dsm.repo.external.web.rest.library.dto.response;

import com.dsm.repo.internal.core.domain.model.library.LibraryModel;
import com.dsm.repo.external.web.rest.library.dto.LibraryElementDto;

import java.util.List;

public record LibraryDetailResponse(
        String id,
        LibraryElementDto.AccessRight accessRight,
        Integer year,
        Integer grade,
        Integer generation,
        String resumeUrl,
        List<LibraryElementDto.ResumeIndex> index
) {
    public LibraryDetailResponse(LibraryModel library, String resumeUrl) {
        this(library.id(), library.accessRight(), library.year(), library.grade(), library.getGeneration(), resumeUrl, library.index());
    }
}