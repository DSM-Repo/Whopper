package com.example.whopper.domain.teacher;

public record TeacherModel(
        String id,
        String name,
        String accountId,
        String password
) {
    public TeacherModel(String name, String accountId, String password) {
        this(null, name, accountId, password);
    }
}
