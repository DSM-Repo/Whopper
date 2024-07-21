package com.example.whopper.domain.teacher.domain;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "teacher_repo")
public class TeacherEntity {

    private String id;
    private String name;
    private String accountId;
    private String password;
}
