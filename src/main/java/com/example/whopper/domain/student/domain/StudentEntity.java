package com.example.whopper.domain.student.domain;

import com.example.whopper.domain.major.domain.MajorEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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

    private ClassInfo classInfo;

    private String profileImagePath;

    @DBRef(lazy = true)
    private MajorEntity major;

    protected StudentEntity() {}

    public void updateMajor(MajorEntity major) {
        this.major = major;
    }
}
