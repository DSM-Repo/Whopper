package com.dsm.repo.internal.data.repository.notice;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoticeMongoRepository extends MongoRepository<NoticeEntity, String> {
}
