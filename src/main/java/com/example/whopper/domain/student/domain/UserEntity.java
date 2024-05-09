package com.example.whopper.domain.student.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "user_repo")
public class UserEntity {
    @Id
    private String id;

    private String name;

    private Integer grade;

    private Integer classNum;

    private Integer number;

    private String profileImagePath;
}
