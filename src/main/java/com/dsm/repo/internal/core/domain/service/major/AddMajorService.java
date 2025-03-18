package com.dsm.repo.internal.core.domain.service.major;

import com.dsm.repo.internal.core.usecase.major.AddMajorUseCase;
import com.dsm.repo.internal.core.domain.model.major.MajorModel;
import com.dsm.repo.internal.data.repository.major.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
