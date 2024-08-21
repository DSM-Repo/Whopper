package com.example.whopper.lagacy.major.application.impl;

import com.example.whopper.lagacy.major.application.usecase.DeleteMajorUseCase;
import com.example.whopper.lagacy.major.dao.MajorRepository;
import com.example.whopper.lagacy.major.exception.MajorNotFoundException;
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
