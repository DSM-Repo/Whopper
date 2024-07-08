package com.example.whopper.domain.student.dao;

import com.example.whopper.domain.student.domain.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentMongoRepository extends MongoRepository<StudentEntity, String> {
    Optional<StudentEntity> findFirstByAccountId(String accountId);
    Boolean existsByAccountId(String accountId);
}
