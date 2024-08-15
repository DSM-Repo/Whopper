package com.example.whopper.domain.major.application.impl;

import com.example.whopper.domain.major.application.usecase.AddMajorUseCase;
import com.example.whopper.domain.major.dao.MajorRepository;
import com.example.whopper.domain.major.domain.MajorEntity;
import com.example.whopper.domain.major.exception.AlreadyExistsMajorNameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddMajorService implements AddMajorUseCase {
    private final MajorRepository majorRepository;

    @Override
    public void add(List<String> majors) {
        var uniqueMajors = new HashSet<>(majors);
        List<String> newMajors = uniqueMajors.stream()
                .filter(major -> !majorRepository.existsByName(major))
                .toList();

        if (!newMajors.isEmpty()) {
            var majorEntities = newMajors.stream()
                    .map(MajorEntity::createEntity)
                    .toList();

            majorRepository.saveAll(majorEntities);
        }
    }
}
