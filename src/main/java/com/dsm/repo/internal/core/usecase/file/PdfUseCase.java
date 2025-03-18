package com.dsm.repo.internal.core.usecase.file;

import org.springframework.web.multipart.MultipartFile;

public interface PdfUseCase {
    String savePdf(MultipartFile multipartFile);
    String getPdfFileUrl(String filePath);
}
