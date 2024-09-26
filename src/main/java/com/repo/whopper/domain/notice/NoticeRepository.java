package com.repo.whopper.domain.notice;

import java.util.Optional;
import java.util.stream.Stream;

public interface NoticeRepository {
    void save(NoticeModel model);
    Optional<NoticeModel> findById(String noticeId);
    Stream<NoticeModel> findAll();
    void deleteById(String noticeId);
}
