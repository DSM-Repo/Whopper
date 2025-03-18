package com.dsm.repo.internal.data.repository.student;

import com.dsm.repo.internal.core.domain.model.student.StudentModel;

import java.util.Optional;

public interface StudentRepository {
    Optional<StudentModel> findByAccountId(String accountId);
    Optional<StudentModel> findById(String id);
    boolean existsByAccountId(String accountId);
    boolean existsById(String id);
    StudentModel save(StudentModel model);
}
