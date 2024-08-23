package com.example.whopper.application.file.impl;

import com.example.whopper.application.file.usecase.ImageUseCase;
import com.example.whopper.infrastructure.aws.s3.AwsS3Properties;
import com.example.whopper.infrastructure.aws.s3.AwsS3FileType;
import com.example.whopper.domain.file.type.ImageType;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.UploadRequest;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class ImageService implements ImageUseCase {
    private static final Set<String> VALID_EXTENSIONS = Set.of(".jpg", ".jpeg", ".png", ".heic", ".svg", ".webp", ".gif");

    private final S3TransferManager s3TransferManager;
    private final AwsS3Properties awsS3Properties;
    private final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

    public String saveImage(MultipartFile multipartFile, ImageType imageType) {
        String originalFileName = multipartFile.getOriginalFilename();
        if (originalFileName == null || !isValidExtension(getExtension(originalFileName))) {
            throw new RuntimeException("Invalid file extension.");
        }

        // Generate a unique key for the file
        String key = generateFileKey(imageType);

        // Upload file and return the key
        uploadFile(multipartFile, key);

        return key;
    }

    public String getFileBaseUrl() {
        return awsS3Properties.url();
    }

    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".")).toLowerCase();
    }

    private String generateFileKey(ImageType imageType) {
        String folder = imageType == ImageType.PROFILE ? awsS3Properties.profileFolder() : awsS3Properties.documentFolder();
        return folder + "/" + UUID.randomUUID();
    }

    private void uploadFile(MultipartFile multipartFile, String key) {
        try {
            // InputStream을 사용하여 파일을 업로드
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(awsS3Properties.bucket())
                    .key(key)
                    .contentType(multipartFile.getContentType())
                    .acl(AwsS3FileType.IMAGE.getCannedAcl())
                    .build();

            var uploadFuture = s3TransferManager.upload(UploadRequest.builder()
                    .putObjectRequest(putObjectRequest)
                    .requestBody(AsyncRequestBody.fromInputStream(multipartFile.getInputStream(), multipartFile.getSize(), executorService)) // ExecutorService 추가
                    .build()).completionFuture();

            uploadFuture.whenComplete((result, exception) -> {
                if (exception != null) {
                    throw new RuntimeException("파일 업로드 중 오류 발생", exception);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("파일 업로드 중 오류 발생", e);
        }
    }

    private boolean isValidExtension(String extension) {
        return VALID_EXTENSIONS.contains(extension);
    }
}