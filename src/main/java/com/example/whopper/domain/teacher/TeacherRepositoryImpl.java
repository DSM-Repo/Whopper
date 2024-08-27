package com.example.whopper.domain.teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
class TeacherRepositoryImpl implements TeacherRepository {
    private final TeacherMongoRepository teacherMongoRepository;
    private final TeacherEntityMapper teacherEntityMapper;

    @Override
    public Optional<TeacherModel> findByAccountId(String accountId) {
        final var result = teacherMongoRepository.findByAccountId(accountId);

        return teacherEntityMapper.toOptionalModel(result);
    }

    @Override
    public Optional<TeacherModel> findById(String id) {
        final var result = teacherMongoRepository.findById(id);

        return teacherEntityMapper.toOptionalModel(result);
    }

    @Override
    public boolean existsByAccountId(String accountId) {
        return teacherMongoRepository.existsByAccountId(accountId);
    }

    @Override
    public boolean existsById(String id) {
        return teacherMongoRepository.existsById(id);
    }

    @Override
    public TeacherModel save(TeacherModel model) {
        final var savedEntity = teacherMongoRepository.save(teacherEntityMapper.toEntity(model));

        return teacherEntityMapper.toModel(savedEntity);
    }
}
