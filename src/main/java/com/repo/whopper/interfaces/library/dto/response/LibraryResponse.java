package com.repo.whopper.interfaces.library.dto.response;

import com.repo.whopper.domain.library.LibraryModel;
import com.repo.whopper.interfaces.library.dto.LibraryElementDto;

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
