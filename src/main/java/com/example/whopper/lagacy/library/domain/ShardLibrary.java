package com.example.whopper.lagacy.library.domain;

public record ShardLibrary(
        String id,
        Integer year,
        Integer grade,
        Integer generation
) {

    public static ShardLibrary fromLibraryEntity(LibraryEntity library) {
        return new ShardLibrary(
                library.getId(),
                library.getYear(),
                library.getGrade(),
                library.getGeneration()
        );
    }
}
