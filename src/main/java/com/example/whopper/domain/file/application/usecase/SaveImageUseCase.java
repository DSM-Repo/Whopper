package com.example.whopper.domain.file.application.usecase;

import com.example.whopper.domain.file.type.ImageType;
import org.springframework.web.multipart.MultipartFile;

public interface SaveImageUseCase {
    String saveImage(MultipartFile multipartFile, ImageType imageType);
    String getFileBaseUrl();
}
