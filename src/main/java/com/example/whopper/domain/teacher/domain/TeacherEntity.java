package com.example.whopper.domain.teacher.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "teacher_repo")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherEntity {
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
}
