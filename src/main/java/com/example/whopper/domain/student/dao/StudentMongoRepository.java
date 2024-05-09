package com.example.whopper.domain.student.dao;

import com.example.whopper.domain.student.domain.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentMongoRepository extends MongoRepository<StudentEntity, String> {
}
