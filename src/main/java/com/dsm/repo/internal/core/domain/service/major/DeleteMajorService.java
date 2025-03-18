package com.dsm.repo.internal.core.domain.service.major;

import com.dsm.repo.internal.core.usecase.major.DeleteMajorUseCase;
import com.dsm.repo.internal.data.repository.major.MajorRepository;
import com.dsm.repo.external.exception.domain.major.MajorNotFoundException;
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
