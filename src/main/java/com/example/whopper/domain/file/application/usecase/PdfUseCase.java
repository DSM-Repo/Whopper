package com.example.whopper.domain.file.application.usecase;

import org.springframework.web.multipart.MultipartFile;

public interface PdfUseCase {
    String savePdf(MultipartFile multipartFile);
    String getPdfFileUrl(String filePath);
}
