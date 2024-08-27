package com.example.whopper.domain.major;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "majors")
class MajorEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;

    protected MajorEntity() {}
}
