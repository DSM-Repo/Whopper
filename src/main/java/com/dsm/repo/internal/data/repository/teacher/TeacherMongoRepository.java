package com.dsm.repo.internal.data.repository.teacher;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

interface TeacherMongoRepository extends MongoRepository<TeacherEntity, String> {
    Optional<TeacherEntity> findByAccountId(String accountId);
    Boolean existsByAccountId(String accountId);
}
