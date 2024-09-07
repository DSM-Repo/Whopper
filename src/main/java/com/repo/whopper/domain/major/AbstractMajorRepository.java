package com.repo.whopper.domain.major;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AbstractMajorRepository implements MajorRepository {
    private final MajorMongoRepository majorMongoRepository;
    private final MajorEntityMapper majorEntityMapper;

    @Override
    public MajorModel save(MajorModel model) {
        final var majorEntity = majorMongoRepository.save(
                majorEntityMapper.toEntity(model)
        );

        return majorEntityMapper.toModel(majorEntity);
    }

    @Override
    public void saveAll(List<MajorModel> entities) {
        majorMongoRepository.saveAll(
                majorEntityMapper.toEntityList(entities)
        );
    }

    @Override
    public Optional<MajorModel> findByName(String name) {
        final var entity = majorMongoRepository.findById(name);

        return majorEntityMapper.toOptionalModel(entity);
    }

    @Override
    public List<MajorModel> findAll() {
        final var entities = majorMongoRepository.findAll();

        return majorEntityMapper.toModelList(entities);
    }

    @Override
    public void delete(MajorModel model) {
        majorMongoRepository.delete(
                majorEntityMapper.toEntity(model)
        );
    }

}
