package com.example.whopper.common.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.cors")
public record CorsProperties(
        String allowHosts,
        String allowHeaders,
        String exposedHeaders
) {
}
