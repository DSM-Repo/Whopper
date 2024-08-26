package com.example.whopper.domain.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
class StudentRepositoryImpl implements StudentRepository {
    private final StudentCrudRepository studentCrudRepository;
    private final StudentEntityMapper studentEntityMapper;

    @Override
    public Optional<StudentModel> findByAccountId(String accountId) {
        var student = studentCrudRepository.findByAccountId(accountId);

        return studentEntityMapper.toOptionalModel(student);
    }

    @Override
    public boolean existsByAccountId(String accountId) {
        return studentCrudRepository.existsByAccountId(accountId);
    }

    @Override
    public boolean existsById(String id) {
        return studentCrudRepository.existsById(id);
    }

    @Override
    public StudentModel save(StudentModel model) {
        var studentEntity = studentCrudRepository.save(studentEntityMapper.toEntity(model));

        return studentEntityMapper.toModel(studentEntity);
    }
}
