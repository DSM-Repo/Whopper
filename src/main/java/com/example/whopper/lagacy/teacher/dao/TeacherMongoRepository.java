package com.example.whopper.lagacy.teacher.dao;

import com.example.whopper.lagacy.teacher.domain.TeacherEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TeacherMongoRepository extends MongoRepository<TeacherEntity, String> {
    Optional<TeacherEntity> findByAccountId(String accountId);
    Boolean existsByAccountId(String accountId);
}
