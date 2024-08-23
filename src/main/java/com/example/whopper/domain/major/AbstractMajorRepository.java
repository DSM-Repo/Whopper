package com.example.whopper.domain.major;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AbstractMajorRepository implements MajorRepository {
    private final MajorMongoRepository majorMongoRepository;

    @Override
    public MajorModel save(MajorModel model) {
        final var majorEntity = majorMongoRepository.save(
                MajorEntityMapper.toEntity(model)
        );

        return MajorEntityMapper.toModel(majorEntity);
    }

    @Override
    public void saveAll(List<MajorModel> entities) {
        majorMongoRepository.saveAll(
                MajorEntityMapper.toEntityList(entities)
        );
    }

    @Override
    public Optional<MajorModel> findByName(String name) {
        final var entity = majorMongoRepository.findByName(name); // Optional<MajorEntity>

        return MajorEntityMapper.toOptionalModel(entity);
    }

    @Override
    public Optional<MajorModel> findById(String majorId) {
        final var entity = majorMongoRepository.findById(majorId);

        return MajorEntityMapper.toOptionalModel(entity);
    }

    @Override
    public List<MajorModel> findAll() {
        final var entities = majorMongoRepository.findAll();

        return MajorEntityMapper.toModelList(entities);
    }

    @Override
    public void delete(MajorModel model) {
        majorMongoRepository.delete(
                MajorEntityMapper.toEntity(model)
        );
    }

    @Override
    public boolean existsByName(String name) {
        return majorMongoRepository.existsByName(name);
    }
}
