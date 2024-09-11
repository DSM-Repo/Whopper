package com.repo.whopper.domain.major;

import lombok.Builder;

@Builder
public record MajorModel(
        String id
) {
    public static MajorModel createNewMajor(String name) {
        return new MajorModel(name);
    }
}
