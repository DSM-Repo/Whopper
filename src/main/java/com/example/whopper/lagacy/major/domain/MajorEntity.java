package com.example.whopper.lagacy.major.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Document(collection = "majors")
public class MajorEntity {
    @Id
    private String id;
    @Field("name")
    @Indexed(unique = true)
    private String name;

    protected MajorEntity() {}

    private MajorEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static MajorEntity createEntity(String name) {
        return new MajorEntity(null, name);
    }
}
