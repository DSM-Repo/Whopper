package com.example.whopper.domain.student;

import java.util.Optional;

public interface StudentRepository {
    Optional<StudentModel> findByAccountId(String accountId);
    boolean existsByAccountId(String accountId);
    boolean existsById(String id);
    StudentModel save(StudentModel model);
}
