package com.example.whopper.domain.major.application.impl;

import com.example.whopper.domain.major.application.usecase.DeleteMajorUseCase;
import com.example.whopper.domain.major.dao.MajorRepository;
import com.example.whopper.domain.major.exception.MajorNotFoundException;
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
