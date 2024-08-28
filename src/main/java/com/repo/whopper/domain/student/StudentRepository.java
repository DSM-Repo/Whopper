package com.repo.whopper.domain.student;

import java.util.Optional;

public interface StudentRepository {
    Optional<StudentModel> findByAccountId(String accountId);
    Optional<StudentModel> findById(String id);
    boolean existsByAccountId(String accountId);
    boolean existsById(String id);
    StudentModel save(StudentModel model);
}
