package com.example.whopper.lagacy.teacher.domain;

import lombok.Getter;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "teacher_repo")
public class TeacherEntity {
    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String accountId;
    private String password;

    @Builder
    public TeacherEntity(String name, String accountId, String password) {
        this.name = name;
        this.accountId = accountId;
        this.password = password;
    }
    protected TeacherEntity() {}
}
