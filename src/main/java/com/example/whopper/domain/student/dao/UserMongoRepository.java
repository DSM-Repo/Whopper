package com.example.whopper.domain.student.dao;

import com.example.whopper.domain.student.domain.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<UserEntity, String> {
}
