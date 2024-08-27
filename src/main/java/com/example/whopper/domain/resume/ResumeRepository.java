package com.example.whopper.domain.resume;

import java.util.Optional;
import java.util.stream.Stream;

public interface ResumeRepository {
    Optional<ResumeModel> findById(String resumeId);
    Optional<ResumeModel> findByWriterId(String writerId);
    Stream<ResumeModel> searchResumes(String name, Integer grade, Integer classNumber, String majorId, String status);
    Stream<ResumeModel> getReleasedResumes();
    ResumeModel save(ResumeModel resume);
    Stream<ResumeModel> getReleasedResumesByGenerationAndYear(int generation, int year);
}
