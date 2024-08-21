package com.example.whopper.lagacy.file.application.usecase;

import com.example.whopper.lagacy.file.type.ImageType;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUseCase {
    String saveImage(MultipartFile multipartFile, ImageType imageType);
    String getFileBaseUrl();
}
