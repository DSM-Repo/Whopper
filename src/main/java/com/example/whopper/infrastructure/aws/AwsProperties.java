package com.example.whopper.infrastructure.aws;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws")
public record AwsProperties(
        String region,
        CredentialsProperties credentials
) {
    public record CredentialsProperties(
            String accessKey,
            String secretKey
    ) {
    }
}