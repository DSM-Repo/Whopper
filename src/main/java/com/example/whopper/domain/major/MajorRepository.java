package com.example.whopper.domain.major;

import java.util.List;
import java.util.Optional;

public interface MajorRepository {
    MajorEntity save(MajorEntity entity);
    void saveAll(List<MajorEntity> entities);
    Optional<MajorEntity> findByName(String name);
    Optional<MajorEntity> findById(String majorId);
    List<MajorEntity> findAll();
    void delete(MajorEntity entity);
    boolean existsByName(String name);
}
