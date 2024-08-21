package com.example.whopper.lagacy.student.domain;

import com.example.whopper.lagacy.major.domain.MajorEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Document(collection = "user_repo")
@Builder
@AllArgsConstructor
public class StudentEntity {
    @Id
    private String id;

    private String accountId;

    private String password;

    @Field("name")
    private String name;

    @Field("classInfo")
    private ClassInfo classInfo;

    private String profileImagePath;

    @Field("major")
    @DBRef(lazy = true)
    private MajorEntity major;

    protected StudentEntity() {}

    public void updateMajor(MajorEntity major) {
        this.major = major;
    }
}
