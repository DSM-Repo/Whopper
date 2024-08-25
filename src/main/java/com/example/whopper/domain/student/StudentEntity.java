package com.example.whopper.domain.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
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
    private Major major;

    protected StudentEntity() {}

    public void updateMajor(Major major) {
        this.major = major;
    }

    public record Major(
            String id,
            String name
    ) {}
}
