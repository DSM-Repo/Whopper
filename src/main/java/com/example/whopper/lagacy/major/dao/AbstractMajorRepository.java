package com.example.whopper.lagacy.major.dao;

import com.example.whopper.lagacy.major.domain.MajorEntity;
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
    public void saveAll(List<MajorEntity> entities) {
        majorMongoRepository.saveAll(entities);
    }

    @Override
    public Optional<MajorEntity> findByName(String name) {
        return majorMongoRepository.findByName(name);
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
