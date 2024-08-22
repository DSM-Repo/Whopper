package com.example.whopper.infra.s3;

import com.example.whopper.infra.config.AwsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.transfer.s3.S3TransferManager;

@Component
public class AwsS3Config {
private final AwsProperties awsProperties;

    @Autowired
    public AwsS3Config(AwsProperties awsProperties) {
        this.awsProperties = awsProperties;
    }

    // https://github.com/aws/aws-sdk-java-v2/issues/1750
    @Bean
    public S3TransferManager s3TransferManager() {
        S3AsyncClient s3AsyncClient = S3AsyncClient
                .crtBuilder()
                .credentialsProvider(DefaultCredentialsProvider.create())
                .region(Region.of(awsProperties.region()))
                .targetThroughputInGbps(20.0)
                .minimumPartSizeInBytes(1L * 1024 * 1024)
                .build();

        return S3TransferManager.builder()
                .s3Client(s3AsyncClient)
                .build();
    }

    @Bean
    public S3Presigner s3Presigner() {
        return S3Presigner.builder()
                .credentialsProvider(DefaultCredentialsProvider.create())
                .region(Region.of(awsProperties.region()))
                .build();
    }
}