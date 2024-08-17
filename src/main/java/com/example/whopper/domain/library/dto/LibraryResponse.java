package com.example.whopper.domain.library.dto;

import com.example.whopper.domain.library.application.model.Library;
import com.example.whopper.domain.library.domain.type.AccessRight;

public record LibraryResponse(
        String id,
        AccessRight access_right,
        Integer year,
        Integer grade,
        Integer generation
) {

    public static LibraryResponse from(Library library) {
        return new LibraryResponse(
                library.id(),
                library.accessRight(),
                library.year(),
                library.grade(),
                library.getGeneration()
        );
    }
}
