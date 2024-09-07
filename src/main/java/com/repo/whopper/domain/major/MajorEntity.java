package com.repo.whopper.domain.major;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Builder
@Getter
@AllArgsConstructor
@Document(collection = "major_repo")
class MajorEntity {
    @MongoId
    private String id;

    protected MajorEntity() {}
}
