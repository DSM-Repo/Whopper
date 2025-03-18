package com.dsm.repo.internal.data.repository.student;

import com.dsm.repo.internal.core.domain.model.student.StudentModel;
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
    public Optional<StudentModel> findById(String studentId) {
        var student = studentCrudRepository.findById(studentId);

        return studentEntityMapper.toOptionalModel(student);
    }

    @Override
    public boolean existsByAccountId(String accountId) {
        return studentCrudRepository.existsByAccountId(accountId);
    }

    @Override
    public boolean existsById(String studentId) {
        return studentCrudRepository.existsById(studentId);
    }

    @Override
    public StudentModel save(StudentModel model) {
        var studentEntity = studentCrudRepository.save(studentEntityMapper.toEntity(model));

        return studentEntityMapper.toModel(studentEntity);
    }
}
