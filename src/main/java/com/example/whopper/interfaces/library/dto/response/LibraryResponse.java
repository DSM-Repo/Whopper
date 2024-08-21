package com.example.whopper.interfaces.library.dto.response;

import com.example.whopper.lagacy.library.application.model.Library;
import com.example.whopper.domain.library.LibraryEntity;
import com.example.whopper.domain.library.type.AccessRight;

public record LibraryResponse(
        String id,
        AccessRight accessRight,
        Integer year,
        Integer grade,
        Integer generation
) {

    public static LibraryResponse fromEntity(LibraryEntity entity) {
        return new LibraryResponse(
                entity.getId(),
                entity.getAccessRight(),
                entity.getYear(),
                entity.getGrade(),
                entity.getGeneration()
        );
    }

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
