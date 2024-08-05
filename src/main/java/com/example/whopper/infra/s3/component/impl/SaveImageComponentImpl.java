package com.example.whopper.infra.s3.component.impl;

import com.example.whopper.infra.s3.component.SaveImageComponent;
import com.example.whopper.infra.s3.AwsS3Properties;
import com.example.whopper.infra.s3.AwsS3FileType;
import com.example.whopper.domain.file.type.ImageType;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.stereotype.Component;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.UploadRequest;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.Callable;

@Component
@RequiredArgsConstructor
public class SaveImageComponentImpl implements SaveImageComponent {

    private final S3TransferManager s3TransferManager;
    private final AwsS3Properties awsS3Properties;

    public Mono<String> saveImage(MultipartFile multipartFile, ImageType imageType) {
        String folder = switch (imageType) {
            case PROFILE -> awsS3Properties.profileFolder();
            case DOCUMENT -> awsS3Properties.documentFolder();
        };

        String extension = getExtension(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        if (!isValidExtension(extension)) {
            return Mono.error(new RuntimeException("Invalid file extension."));
        }

        // Generate a unique key for the file
        String key = folder + "/" + UUID.randomUUID() + extension;

        // Convert MultipartFile to Flux<DataBuffer> and handle IOException
        return Mono.defer(() -> {
            Flux<DataBuffer> fileContent = DataBufferUtils.readInputStream(
                    (Callable<InputStream>) multipartFile::getInputStream,
                    (DataBufferFactory) new DefaultDataBufferFactory(),
                    (int) multipartFile.getSize()
            );
            return uploadFile(fileContent, key).then(Mono.just(key));
        });
    }

    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".")).toLowerCase();
    }


    private Mono<Void> uploadFile(Flux<DataBuffer> fileContent, String key) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(awsS3Properties.bucket())
                .key(key)
                .acl(AwsS3FileType.IMAGE.getCannedAcl())
                .build();

        return Mono.fromFuture(() -> s3TransferManager.upload(UploadRequest.builder()
                        .putObjectRequest(putObjectRequest)
                        .requestBody(AsyncRequestBody.fromPublisher(fileContent.map(DataBuffer::asByteBuffer)))
                        .build()).completionFuture())
                .then(); // Mono<CompletedUpload>를 Mono<Void>로 변환
    }

    @Override
    public String getFileBaseUrl() {
        return awsS3Properties.url();
    }

    public static class Extensions {
        public static final String JPG = ".jpg";
        public static final String JPEG = ".jpeg";
        public static final String PNG = ".png";
        public static final String HEIC = ".heic";
        public static final String SVG = ".svg";
    }

    private boolean isValidExtension(String extension) {
        return extension.equals(Extensions.JPG) || extension.equals(Extensions.JPEG) ||
                extension.equals(Extensions.PNG) || extension.equals(Extensions.HEIC) ||
                extension.equals(Extensions.SVG);
    }
}