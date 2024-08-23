package com.example.whopper.domain.major;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Document(collection = "majors")
class MajorEntity {
    @Id
    private String id;

    @Field("name")
    @Indexed(unique = true)
    private String name;

    protected MajorEntity() {}

    MajorEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    static MajorEntity createEntity(String name) {
        return new MajorEntity(null, name);
    }
}
