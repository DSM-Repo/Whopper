package com.dsm.repo.internal.data.repository.notice;

import com.dsm.repo.internal.core.domain.model.notice.NoticeModel;

import java.util.Optional;
import java.util.stream.Stream;

public interface NoticeRepository {
    void save(NoticeModel model);
    Optional<NoticeModel> findById(String noticeId);
    Stream<NoticeModel> findAll();
    void deleteById(String noticeId);
}
