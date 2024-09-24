package com.repo.whopper.domain.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepository {
    private final NoticeMongoRepository noticeMongoRepository;
    private final NoticeEntityMapper noticeEntityMapper;

    @Override
    public NoticeModel save(NoticeModel model) {
        final var entity =  noticeEntityMapper.toEntity(model);

        return noticeEntityMapper.toModel(noticeMongoRepository.save(entity));
    }

    @Override
    public Optional<NoticeModel> findById(String noticeId) {
        return noticeEntityMapper.toOptionalModel(noticeMongoRepository.findById(noticeId));
    }

    @Override
    public Stream<NoticeModel> findAll() {
        final var result = noticeMongoRepository.findAll().stream();

        return noticeEntityMapper.toStreamLibraryModel(result);
    }
}
