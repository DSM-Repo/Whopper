package com.example.whopper.domain.library.dto;

import com.example.whopper.domain.library.application.model.Library;
import com.example.whopper.domain.library.domain.LibraryEntity;
import com.example.whopper.domain.library.domain.type.AccessRight;

public record LibraryResponse(
        String id,
        AccessRight access_right,
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
