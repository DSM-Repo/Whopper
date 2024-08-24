package com.example.whopper.application.file.usecase;

import com.example.whopper.domain.file.type.ImageType;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUseCase {
    String saveImage(MultipartFile multipartFile, ImageType imageType);
    String getFileBaseUrl();
    void deleteImage(String url);
}
