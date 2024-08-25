package com.example.whopper.domain.resume;

import com.example.whopper.interfaces.resume.dto.ResumeElementDto;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
abstract class AbstractResumeRepository implements ResumeRepository {
    private final ResumeMongoRepository resumeMongoRepository;
    protected final ResumeEntityMapper resumeEntityMapper;
    private final ResumeElementMapper resumeElementMapper;

    @Override
    public Optional<ResumeModel> findById(String id) {
        return resumeEntityMapper.toOptionalModel(resumeMongoRepository.findById(id));
    }

    @Override
    public Optional<ResumeModel> findByWriterId(String id) {
        return resumeEntityMapper.toOptionalModel(resumeMongoRepository.findByWriterId(id));
    }

    @Override
    public Stream<ResumeModel> getReleasedDocuments() {
        return resumeMongoRepository.findAllByStatus(resumeElementMapper.toStatusEntity(ResumeElementDto.Status.RELEASED))
                .map(resumeEntityMapper::toModel);
    }

    @Override
    public Stream<ResumeModel> getReleasedDocumentsByGenerationAndYear(int generation, int year) {
        return resumeMongoRepository.findAllByWriterSchoolInfoGenerationAndYearAndStatus(generation, year, resumeElementMapper.toStatusEntity(ResumeElementDto.Status.RELEASED))
                .map(resumeEntityMapper::toModel);
    }

    @Override
    public ResumeModel save(ResumeModel resume) {
        final var entity = resumeEntityMapper.toEntity(resume);

        return resumeEntityMapper.toModel(resumeMongoRepository.save(entity));
    }

    @Override
    public Boolean existsByDocumentId(String documentId) {
        return resumeMongoRepository.existsById(documentId);
    }
}
