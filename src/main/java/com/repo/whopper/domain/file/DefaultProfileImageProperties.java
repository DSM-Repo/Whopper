package com.repo.whopper.domain.file;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "default.profile")
public record DefaultProfileImageProperties(
        String imageUrl
) {
}
