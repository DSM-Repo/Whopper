package com.example.whopper.domain.major.dao;

import com.example.whopper.domain.major.domain.DefaultMajorFacade;
import com.example.whopper.domain.major.domain.MajorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AbstractMajorRepository implements MajorRepository {
    private final MajorMongoRepository majorMongoRepository;

    @Override
    public MajorEntity save(MajorEntity entity) {
        return majorMongoRepository.save(entity);
    }

    @Override
    public MajorEntity getById(String majorId) {
        return findById(majorId)
                .orElse(null);
    }

    @Override
    public Optional<MajorEntity> findById(String majorId) {
        return majorMongoRepository.findById(majorId);
    }

    @Override
    public List<MajorEntity> findAll() {
        return majorMongoRepository.findAll();
    }

    @Override
    public void delete(MajorEntity entity) {
        majorMongoRepository.delete(entity);
    }

    @Override
    public boolean existsByName(String name) {
        return majorMongoRepository.existsByName(name);
    }
}
