package com.repo.whopper.domain.notice;

import java.util.Optional;
import java.util.stream.Stream;

public interface NoticeRepository {
    Optional<NoticeModel> findById(String libraryId);
    Stream<NoticeModel> findAll();
}
