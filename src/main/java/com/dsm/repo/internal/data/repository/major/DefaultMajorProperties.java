package com.dsm.repo.internal.data.repository.major;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "default.major")
public record DefaultMajorProperties(
        String name
) {
}
