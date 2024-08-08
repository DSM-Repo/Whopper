package com.example.whopper.domain.file.application.usecase;

import org.springframework.web.multipart.MultipartFile;

public interface SavePdfUseCase {
    String savePdf(MultipartFile multipartFile);
}
