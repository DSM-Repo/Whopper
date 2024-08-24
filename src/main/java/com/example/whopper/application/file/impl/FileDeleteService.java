package com.example.whopper.application.file.impl;

import io.awspring.cloud.s3.S3Operations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class FileDeleteService {
    private final S3Operations s3Operations;

    void deleteByUrl(String url) {
        s3Operations.deleteObject(url);
    }
}
