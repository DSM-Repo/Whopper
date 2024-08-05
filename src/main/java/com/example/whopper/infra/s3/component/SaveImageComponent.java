package com.example.whopper.infra.s3.component;

import com.example.whopper.domain.file.type.ImageType;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

public interface SaveImageComponent {
    Mono<String> saveImage(MultipartFile monoFilePart, ImageType imageType);
    String getFileBaseUrl();
}
