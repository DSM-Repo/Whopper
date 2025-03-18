package com.dsm.repo.internal.data.property.file;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "default.profile")
public record DefaultProfileImageProperties(
        String imageUrl
) {
}
