package com.repo.whopper.domain.notice;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoticeMongoRepository extends MongoRepository<NoticeEntity, String> {
}
