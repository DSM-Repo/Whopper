package com.dsm.repo.external.aws.s3;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.s3")
public record AwsS3Properties(
        String url,
        String bucket,
        String profileFolder,
        String resumeFolder,
        String pdfFolder
) {
}
