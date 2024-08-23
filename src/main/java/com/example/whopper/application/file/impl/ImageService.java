package com.example.whopper.application.file.impl;

import com.example.whopper.application.file.usecase.ImageUseCase;
import com.example.whopper.infrastructure.aws.s3.AwsS3Properties;
import com.example.whopper.domain.file.type.ImageType;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ImageService implements ImageUseCase {
    private static final Set<String> VALID_EXTENSIONS = Set.of(".jpg", ".jpeg", ".png", ".heic", ".svg", ".webp", ".gif");

    private final AwsS3Properties awsS3Properties;
    private final FileUploadService fileUploadService;

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

    private boolean isValidExtension(String extension) {
        return VALID_EXTENSIONS.contains(extension);
    }
}