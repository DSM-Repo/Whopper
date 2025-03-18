package com.dsm.repo.internal.data.repository.resume;

import com.dsm.repo.external.web.rest.resume.dto.ResumeElementDto;
import com.dsm.repo.internal.core.domain.model.resume.ResumeModel;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
abstract class AbstractResumeRepository implements ResumeRepository {
    private final ResumeMongoRepository resumeMongoRepository;
    protected final ResumeEntityMapper resumeEntityMapper;
    private final ResumeElementMapper resumeElementMapper;

    @Override
    public Optional<ResumeModel> findById(String resumeId) {
        return resumeEntityMapper.toOptionalModel(resumeMongoRepository.findById(resumeId));
    }

    @Override
    public Optional<ResumeModel> findByWriterAccountId(String accountId) {
        return resumeEntityMapper.toOptionalModel(resumeMongoRepository.findByWriterAccountId(accountId));
    }

    @Override
    public Stream<ResumeModel> getReleasedResumes() {
        return resumeMongoRepository.findAllByStatus(resumeElementMapper.toStatusEntity(ResumeElementDto.Status.RELEASED))
                .map(resumeEntityMapper::toModel);
    }

    @Override
    public Stream<ResumeModel> getReleasedResumesByGenerationAndYear(int generation, int year) {
        return resumeMongoRepository.findAllByWriterSchoolInfoGenerationAndYearAndStatus(generation, year, resumeElementMapper.toStatusEntity(ResumeElementDto.Status.RELEASED))
                .map(resumeEntityMapper::toModel);
    }

    @Override
    public ResumeModel save(ResumeModel resume) {
        final var entity = resumeEntityMapper.toEntity(resume);

        return resumeEntityMapper.toModel(resumeMongoRepository.save(entity));
    }
}