package com.example.whopper.domain.resume;

import com.example.whopper.interfaces.resume.dto.ResumeElementDto;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
abstract class AbstractResumeRepository implements ResumeRepository {
    private final ResumeMongoRepository resumeMongoRepository;

    @Override
    public Optional<ResumeModel> findById(String id) {
        return ResumeEntityMapper.toOptionalModel(resumeMongoRepository.findById(id));
    }

    @Override
    public Optional<ResumeModel> findByWriterId(String id) {
        return ResumeEntityMapper.toOptionalModel(resumeMongoRepository.findByWriterId(id));
    }

    @Override
    public Stream<ResumeModel> getReleasedDocuments() {
        return resumeMongoRepository.findAllByStatus(ResumeElementMapper.toStatusEntity(ResumeElementDto.Status.RELEASED))
                .map(ResumeEntityMapper::toModel);
    }

    @Override
    public Stream<ResumeModel> getReleasedDocumentsByGenerationAndYear(int generation, int year) {
        return resumeMongoRepository.findAllByWriterSchoolInfoGenerationAndYearAndStatus(generation, year, ResumeElementMapper.toStatusEntity(ResumeElementDto.Status.RELEASED))
                .map(ResumeEntityMapper::toModel);
    }

    @Override
    public ResumeModel save(ResumeModel resume) {
        final var entity = ResumeEntityMapper.toEntity(resume);

        return ResumeEntityMapper.toModel(resumeMongoRepository.save(entity));
    }

    @Override
    public Boolean existsByDocumentId(String documentId) {
        return resumeMongoRepository.existsById(documentId);
    }
}
