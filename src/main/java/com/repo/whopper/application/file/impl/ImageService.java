package com.repo.whopper.application.file.impl;

import com.repo.whopper.application.file.usecase.ImageUseCase;
import com.repo.whopper.infrastructure.aws.s3.AwsS3Properties;
import com.repo.whopper.domain.file.type.ImageType;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ImageService implements ImageUseCase {
    private static final Set<String> VALID_EXTENSIONS = Set.of(".jpg", ".jpeg", ".png", ".heic", ".svg", ".webp", ".gif");

    private final AwsS3Properties awsS3Properties;
    private final FileUploadService fileUploadService;
    private final FileDeleteService fileDeleteService;

    @Override
    @Transactional
    public String saveImage(MultipartFile multipartFile, ImageType imageType) {
        String originalFileName = multipartFile.getOriginalFilename();
        if (originalFileName == null || !isValidExtension(getExtension(originalFileName))) {
            throw new RuntimeException("Invalid file extension.");
        }

        // Generate a unique key for the file
        String key = generateFileKey(imageType);

        // Upload file and return the key
        fileUploadService.uploadFile(multipartFile, key);

        return key;
    }

    @Override
    @Transactional
    public String getFileBaseUrl() {
        return awsS3Properties.url();
    }

    @Override
    @Transactional
    public void deleteImage(String url) {
        final var key = url.substring(awsS3Properties.url().length());

        fileDeleteService.deleteByKey(key);
    }

    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".")).toLowerCase();
    }

    private String generateFileKey(ImageType imageType) {
        String folder = imageType == ImageType.PROFILE ? awsS3Properties.profileFolder() : awsS3Properties.resumeFolder();
        return folder + "/" + UUID.randomUUID();
    }

    private boolean isValidExtension(String extension) {
        return VALID_EXTENSIONS.contains(extension);
    }
}