package com.example.whopper.domain.teacher.domain;

import lombok.Getter;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "teacher_repo")
public class TeacherEntity {
    @Id
    private String id;
    private String name;
    private String accountId;
    private String password;

    @Builder
    public TeacherEntity(String name, String account_id, String password) {
        this.name = name;
        this.accountId = account_id;
        this.password = password;
    }
    protected TeacherEntity() {}
}
