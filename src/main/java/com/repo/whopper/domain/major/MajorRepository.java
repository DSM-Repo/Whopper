package com.repo.whopper.domain.major;

import java.util.List;
import java.util.Optional;

public interface MajorRepository {
    MajorModel save(MajorModel entity);
    void saveAll(List<MajorModel> entities);
    Optional<MajorModel> findByName(String name);
    List<MajorModel> findAll();
    void delete(MajorModel entity);
}
