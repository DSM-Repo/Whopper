package com.example.whopper.domain.student.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "user_repo")
@Builder
@AllArgsConstructor
public class StudentEntity {
    @Id
    private String id;

    private String accountId;

    private String password;

    private String name;

    private Integer grade;

    private Integer classNum;

    private Integer number;

    private String profileImagePath;

    protected StudentEntity() {}

}
