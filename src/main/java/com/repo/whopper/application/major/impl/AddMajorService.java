package com.repo.whopper.application.major.impl;

import com.repo.whopper.application.major.usecase.AddMajorUseCase;
import com.repo.whopper.domain.major.MajorModel;
import com.repo.whopper.domain.major.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AddMajorService implements AddMajorUseCase {
    private final MajorRepository majorRepository;

    @Override
    @Transactional
    public void add(List<String> majors) {
        Set<String> uniqueMajors = Set.copyOf(majors);

        if (!uniqueMajors.isEmpty()) {
            var majorEntities = uniqueMajors.stream()
                    .map(MajorModel::createNewMajor)
                    .toList();

            majorRepository.saveAll(majorEntities);
        }
    }
}
