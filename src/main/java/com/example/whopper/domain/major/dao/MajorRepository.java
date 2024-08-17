package com.example.whopper.domain.major.dao;

import com.example.whopper.domain.major.domain.MajorEntity;

import java.util.List;
import java.util.Optional;

public interface MajorRepository {
    MajorEntity save(MajorEntity entity);
    void saveAll(List<MajorEntity> entities);
    MajorEntity getById(String majorId);
    Optional<MajorEntity> findById(String majorId);
    List<MajorEntity> findAll();
    void delete(MajorEntity entity);
    boolean existsByName(String name);
}
