package com.example.whopper.domain.major.application.impl;

import com.example.whopper.domain.major.application.usecase.AddMajorUseCase;
import com.example.whopper.domain.major.dao.MajorRepository;
import com.example.whopper.domain.major.domain.MajorEntity;
import com.example.whopper.domain.major.exception.AlreadyExistsMajorNameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddMajorService implements AddMajorUseCase {
    private final MajorRepository majorRepository;

    @Override
    public void add(String majorName) {
        if (majorRepository.existsByName(majorName)) {
            throw AlreadyExistsMajorNameException.EXCEPTION;
        }

        majorRepository.save(MajorEntity.createEntity(majorName));
    }
}
