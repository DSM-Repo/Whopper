package com.example.whopper.domain.major;

import java.util.List;
import java.util.Optional;

public interface MajorRepository {
    MajorModel save(MajorModel entity);
    void saveAll(List<MajorModel> entities);
    Optional<MajorModel> findByName(String name);
    Optional<MajorModel> findById(String majorId);
    List<MajorModel> findAll();
    void delete(MajorModel entity);
    boolean existsByName(String name);
}
