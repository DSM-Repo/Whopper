package com.dsm.repo.internal.core.domain.model.major;

import lombok.Builder;

@Builder
public record MajorModel(
        String id
) {
    public static MajorModel createNewMajor(String name) {
        return new MajorModel(name);
    }
}
