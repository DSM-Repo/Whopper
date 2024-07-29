package com.example.whopper.domain.major.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "majors")
public record MajorEntity(
        @Id
        String id,
        String name
) {
    public static MajorEntity createEntity(String name) {
        return new MajorEntity(null, name);
    }

    public static final MajorEntity EMPTY_MAJOR_ENTITY = new MajorEntity(null, "전공 미정");

}
