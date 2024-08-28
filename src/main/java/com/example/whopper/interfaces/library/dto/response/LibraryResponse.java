package com.example.whopper.interfaces.library.dto.response;

import com.example.whopper.domain.library.LibraryModel;
import com.example.whopper.interfaces.library.dto.LibraryElementDto;

public record LibraryResponse(
        String id,
        LibraryElementDto.AccessRight accessRight,
        Integer year,
        Integer grade,
        Integer generation
) {

    public static LibraryResponse fromEntity(LibraryModel model) {
        return new LibraryResponse(
                model.id(),
                model.accessRight(),
                model.year(),
                model.grade(),
                model.getGeneration()
        );
    }
}
