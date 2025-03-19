package com.dsm.repo.internal.data.repository.resume;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.stream.Stream;

interface ResumeMongoRepository extends MongoRepository<ResumeEntity, String> {
    Optional<ResumeEntity> findByWriterId(String id);
    Stream<ResumeEntity> findAllByStatus(ResumeEntity.Status status);
    Stream<ResumeEntity> findAllByWriterSchoolInfoGenerationAndYearAndStatus(int generation, int year, ResumeEntity.Status status);

}
