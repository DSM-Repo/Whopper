package com.dsm.repo.internal.data.repository.notice;

import com.dsm.repo.internal.core.domain.model.notice.NoticeModel;
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
    public void save(NoticeModel model) {
        final var entity =  noticeEntityMapper.toEntity(model);

        noticeMongoRepository.save(entity);
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

    @Override
    public void deleteById(String noticeId) {
        noticeMongoRepository.deleteById(noticeId);
    }
}
