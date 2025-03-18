package com.dsm.repo.internal.data.repository.major;

import com.dsm.repo.internal.core.domain.model.major.MajorModel;

import java.util.List;
import java.util.Optional;

public interface MajorRepository {
    MajorModel save(MajorModel entity);
    void saveAll(List<MajorModel> entities);
    Optional<MajorModel> findByName(String name);
    List<MajorModel> findAll();
    void delete(MajorModel entity);
}
