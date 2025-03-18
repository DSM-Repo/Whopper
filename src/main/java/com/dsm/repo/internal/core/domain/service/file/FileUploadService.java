package com.dsm.repo.internal.core.domain.service.file;

import com.dsm.repo.external.exception.domain.file.PdfUploadFailedException;
import com.dsm.repo.external.aws.s3.AwsS3Properties;
import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Operations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
class FileUploadService {
    private final S3Operations s3Operations;
    private final AwsS3Properties s3Properties;

    void uploadFile(MultipartFile multipartFile, String key) {
        try {
            s3Operations.upload(
                    s3Properties.bucket(),
                    key,
                    multipartFile.getInputStream(),
                    ObjectMetadata.builder()
                            .contentType(multipartFile.getContentType())
                            .build()
            );
        } catch (Exception e) {
            throw PdfUploadFailedException.EXCEPTION;
        }
    }

}
