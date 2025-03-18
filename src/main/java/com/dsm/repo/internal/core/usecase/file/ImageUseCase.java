package com.dsm.repo.internal.core.usecase.file;

import com.dsm.repo.internal.data.property.file.type.ImageType;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUseCase {
    String saveImage(MultipartFile multipartFile, ImageType imageType);
    String getFileBaseUrl();
    void deleteImage(String url);
}
