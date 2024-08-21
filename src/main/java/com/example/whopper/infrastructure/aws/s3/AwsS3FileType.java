package com.example.whopper.infrastructure.aws.s3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;

@RequiredArgsConstructor
@Getter
public enum AwsS3FileType {
    PDF(ObjectCannedACL.AUTHENTICATED_READ),
    IMAGE(ObjectCannedACL.PUBLIC_READ);

    private final ObjectCannedACL cannedAcl;
}