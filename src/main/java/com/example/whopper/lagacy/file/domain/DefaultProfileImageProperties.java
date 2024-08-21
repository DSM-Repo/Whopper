package com.example.whopper.lagacy.file.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "default.profile")
public record DefaultProfileImageProperties(
        String imageUrl
) {
}
