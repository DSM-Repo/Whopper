package com.repo.whopper.domain.major;

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
