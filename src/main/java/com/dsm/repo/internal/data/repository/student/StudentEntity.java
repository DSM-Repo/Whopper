package com.dsm.repo.internal.data.repository.student;

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
    private String major;

    /* value objects */

    record ClassInfo(
            Integer grade,
            Integer classNumber,
            Integer number,
            String schoolNumber
    ) {
    }

    /* constructor */
    protected StudentEntity() {}
}
