package com.dsm.repo.internal.data.repository.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@AllArgsConstructor
@Document(collection = "teacher_repo")
class TeacherEntity {
    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String accountId;
    private String password;

    protected TeacherEntity() {}
}
