package com.example.whopper.domain.major.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultMajorFacade {
    private DefaultMajorProperties defaultMajorProperties;

    public MajorEntity getDefaultMajor() {
        return new MajorEntity(defaultMajorProperties.majorId(), defaultMajorProperties.name());
    }
}
