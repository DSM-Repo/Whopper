package com.dsm.repo.external.web.rest.library.dto.response;

import com.dsm.repo.internal.core.domain.model.library.LibraryModel;
import com.dsm.repo.external.web.rest.library.dto.LibraryElementDto;

public record LibraryResponse(
        String id,
        LibraryElementDto.AccessRight accessRight,
        Integer year,
        Integer grade,
        Integer generation
) {
    public static LibraryResponse fromModel(LibraryModel model) {
        return new LibraryResponse(
                model.id(),
                model.accessRight(),
                model.year(),
                model.grade(),
                model.getGeneration()
        );
    }
}
