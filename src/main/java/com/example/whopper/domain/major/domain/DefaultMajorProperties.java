package com.example.whopper.domain.major.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "default.major")
public record DefaultMajorProperties(
        String majorId,
        String name
) {
}
