package com.example.whopper.infra.s3;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.s3")
public record AwsS3Properties(
        String url,
        String bucket,
        String profileFolder,
        String documentFolder,
        String pdfFolder
) {
}
