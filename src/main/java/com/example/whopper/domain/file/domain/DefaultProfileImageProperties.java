package com.example.whopper.domain.file.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "default.profile")
public record DefaultProfileImageProperties(
        String imageUrl
) {
}
