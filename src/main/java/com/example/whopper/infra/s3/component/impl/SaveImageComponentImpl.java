package com.example.whopper.infra.s3.component.impl;

import com.example.whopper.infra.s3.component.SaveImageComponent;
import com.example.whopper.infra.s3.AwsS3Properties;
import com.example.whopper.infra.s3.AwsS3FileType;
import com.example.whopper.domain.file.type.ImageType;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.stereotype.Component;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.UploadRequest;

import java.util.*;

@Component
@RequiredArgsConstructor
public class SaveImageComponentImpl implements SaveImageComponent {
    private static final Set<String> VALID_EXTENSIONS = Set.of(".jpg", ".jpeg", ".png", ".heic", ".svg", ".webp", ".gif");

    private final S3TransferManager s3TransferManager;
    private final AwsS3Properties awsS3Properties;

    @Override
    public String getFileBaseUrl() {
        return awsS3Properties.url();
    }

    @Override
    public Mono<String> saveImage(MultipartFile multipartFile, ImageType imageType) {
        String originalFileName = multipartFile.getOriginalFilename();
        if (originalFileName == null || !isValidExtension(getExtension(originalFileName))) {
            return Mono.error(new RuntimeException("Invalid file extension."));
        }

        // Generate a unique key for the file
        String key = generateFileKey(originalFileName, imageType);

        // Convert MultipartFile to Flux<DataBuffer> and handle IOException
        return Mono.defer(() -> uploadFile(multipartFile, key)
                .thenReturn(key));
    }

    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".")).toLowerCase();
    }

    private String generateFileKey(String originalFileName, ImageType imageType) {
        String folder = imageType == ImageType.PROFILE ? awsS3Properties.profileFolder() : awsS3Properties.documentFolder();

        return folder + "/" + UUID.randomUUID() + originalFileName;
    }

    private Mono<Void> uploadFile(MultipartFile multipartFile, String key) {
        return Mono.fromCallable(() -> DataBufferUtils.readInputStream(
                        multipartFile::getInputStream,
                        new DefaultDataBufferFactory(),
                        (int) multipartFile.getSize()))
                .flatMap(fileContent -> {
                    PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                            .bucket(awsS3Properties.bucket())
                            .key(key)
                            .contentType(multipartFile.getContentType())
                            .acl(AwsS3FileType.IMAGE.getCannedAcl())
                            .build();

                    UploadRequest uploadRequest = UploadRequest.builder()
                            .putObjectRequest(putObjectRequest)
                            .requestBody(AsyncRequestBody.fromPublisher(fileContent.map(DataBuffer::asByteBuffer)))
                            .build();

                    return Mono.fromFuture(s3TransferManager.upload(uploadRequest).completionFuture());
                })
                .then();
    }

    private boolean isValidExtension(String extension) {
        return VALID_EXTENSIONS.contains(extension);
    }
}