package com.dsm.repo.internal.data.repository.major;

import com.dsm.repo.internal.core.domain.model.major.MajorModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultMajorFacade {
    private final DefaultMajorProperties defaultMajorProperties;
    private final MajorRepository majorRepository;

    public MajorModel getDefaultMajor() {
        return majorRepository.findByName(defaultMajorProperties.name())
                .orElseGet(() -> majorRepository.save(MajorModel.createNewMajor(defaultMajorProperties.name())));
    }
}
