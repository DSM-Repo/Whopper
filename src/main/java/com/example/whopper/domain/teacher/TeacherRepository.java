package com.example.whopper.domain.teacher;

import java.util.Optional;

public interface TeacherRepository {
    Optional<TeacherModel> findByAccountId(String accountId);
    Optional<TeacherModel> findById(String id);
    boolean existsByAccountId(String accountId);
    boolean existsById(String id);
    TeacherModel save(TeacherModel model);
}
