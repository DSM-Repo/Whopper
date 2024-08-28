package com.repo.whopper.application.major.impl;

import com.repo.whopper.application.major.usecase.AddMajorUseCase;
import com.repo.whopper.domain.major.MajorModel;
import com.repo.whopper.domain.major.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddMajorService implements AddMajorUseCase {
    private final MajorRepository majorRepository;

    @Override
    @Transactional
    public void add(List<String> majors) {
        var uniqueMajors = new HashSet<>(majors);
        List<String> newMajors = uniqueMajors.stream()
                .filter(major -> !majorRepository.existsByName(major))
                .toList();

        if (!newMajors.isEmpty()) {
            var majorEntities = newMajors.stream()
                    .map(MajorModel::createNewMajor)
                    .toList();

            majorRepository.saveAll(majorEntities);
        }
    }
}
