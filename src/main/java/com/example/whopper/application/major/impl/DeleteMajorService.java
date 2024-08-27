package com.example.whopper.application.major.impl;

import com.example.whopper.application.major.usecase.DeleteMajorUseCase;
import com.example.whopper.domain.major.MajorRepository;
import com.example.whopper.common.exception.major.MajorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteMajorService implements DeleteMajorUseCase {
    private final MajorRepository majorRepository;

    @Override
    @Transactional
    public void delete(String majorId) {
        var major = majorRepository.findById(majorId)
                .orElseThrow(() -> MajorNotFoundException.EXCEPTION);

        majorRepository.delete(major);
    }
}
