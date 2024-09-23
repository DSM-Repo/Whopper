package com.repo.whopper.domain.notice;

import java.util.Optional;
import java.util.stream.Stream;

public interface NoticeRepository {
    NoticeModel save(NoticeModel model);
    Optional<NoticeModel> findById(String noticeId);
    Stream<NoticeModel> findAll();
}
