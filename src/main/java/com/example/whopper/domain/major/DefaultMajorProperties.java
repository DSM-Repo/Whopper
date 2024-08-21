package com.example.whopper.domain.major;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "default.major")
public record DefaultMajorProperties(
        String name
) {
}
