package com.example.whopper.domain.major.domain;

import com.example.whopper.domain.major.dao.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultMajorFacade {
    private final DefaultMajorProperties defaultMajorProperties;
    private final MajorRepository majorRepository;

    public MajorEntity getDefaultMajor() {
        return majorRepository.findById(defaultMajorProperties.majorId())
                .orElseGet(() -> majorRepository.save(MajorEntity.createEntity(defaultMajorProperties.name())));
    }
}
