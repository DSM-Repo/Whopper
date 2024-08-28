package com.repo.whopper.domain.major;

public record MajorModel(
        String id,
        String name
) {
    public static MajorModel createNewMajor(String newName) {
        return new MajorModel(null, newName);
    }
}
