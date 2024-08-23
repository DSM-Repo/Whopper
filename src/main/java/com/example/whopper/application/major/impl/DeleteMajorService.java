package com.example.whopper.application.major.impl;

import com.example.whopper.application.major.usecase.DeleteMajorUseCase;
import com.example.whopper.domain.major.MajorRepository;
import com.example.whopper.common.exception.major.MajorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteMajorService implements DeleteMajorUseCase {
    private final MajorRepository majorRepository;

    @Override
    public void delete(String majorId) {
        var majorEntity = majorRepository.findById(majorId);

        if (majorEntity.isEmpty()) {
            throw MajorNotFoundException.EXCEPTION;
        }

        majorRepository.delete(majorEntity.get());
    }
}
