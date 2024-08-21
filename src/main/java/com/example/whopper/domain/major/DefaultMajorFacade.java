package com.example.whopper.domain.major;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultMajorFacade {
    private final DefaultMajorProperties defaultMajorProperties;
    private final MajorRepository majorRepository;

    public MajorEntity getDefaultMajor() {
        return majorRepository.findByName(defaultMajorProperties.name())
                .orElseGet(() -> majorRepository.save(MajorEntity.createEntity(defaultMajorProperties.name())));
    }
}
