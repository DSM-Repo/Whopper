package com.example.whopper.domain.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@AllArgsConstructor
@Document(collection = "user_repo")
class StudentEntity {
    @Id
    private String id;
    private String accountId;
    private String password;
    private String name;
    private ClassInfo classInfo;
    private String profileImagePath;
    private Major major;

    /* value objects */

    record ClassInfo(
            Integer grade,
            Integer classNumber,
            Integer number,
            String schoolNumber
    ) {
    }

    record Major(
            String id,
            String name
    ) {}

    /* constructor */
    protected StudentEntity() {}
}
