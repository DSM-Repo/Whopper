package com.dsm.repo.internal.data.repository.teacher;

import com.dsm.repo.internal.core.domain.model.teacher.TeacherModel;

import java.util.Optional;

public interface TeacherRepository {
    Optional<TeacherModel> findByAccountId(String accountId);
    Optional<TeacherModel> findById(String teacherId);
    boolean existsByAccountId(String accountId);
    boolean existsById(String teacherId);
    TeacherModel save(TeacherModel model);
}
