package com.dsm.repo.internal.data.repository.student;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface StudentCrudRepository extends CrudRepository<StudentEntity, String> {
    Optional<StudentEntity> findByAccountId(String accountId);
    Boolean existsByAccountId(String accountId);
}
