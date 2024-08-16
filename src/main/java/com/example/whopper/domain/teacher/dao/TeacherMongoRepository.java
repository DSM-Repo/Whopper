package com.example.whopper.domain.teacher.dao;

import com.example.whopper.domain.teacher.domain.TeacherEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TeacherMongoRepository extends MongoRepository<TeacherEntity, String> {
    Optional<TeacherEntity> findByAccountId(String accountId);
    Boolean existsByAccountId(String accountId);
}
