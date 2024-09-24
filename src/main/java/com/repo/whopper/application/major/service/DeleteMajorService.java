package com.repo.whopper.application.major.service;

import com.repo.whopper.application.major.usecase.DeleteMajorUseCase;
import com.repo.whopper.domain.major.MajorRepository;
import com.repo.whopper.common.exception.major.MajorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteMajorService implements DeleteMajorUseCase {
    private final MajorRepository majorRepository;

    @Override
    @Transactional
    public void delete(String majorName) {
        final var major = majorRepository.findByName(majorName)
                .orElseThrow(() -> MajorNotFoundException.EXCEPTION);

        majorRepository.delete(major);
    }
}
