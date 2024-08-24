package com.example.whopper.application.file.impl;

import com.example.whopper.application.file.usecase.PdfUseCase;
import com.example.whopper.common.exception.file.ExtensionNotMatchException;
import com.example.whopper.infrastructure.aws.s3.AwsS3Properties;
import io.awspring.cloud.s3.S3Operations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PdfService implements PdfUseCase {

    private final AwsS3Properties awsS3Properties;
    private final S3Operations s3Operations;
    private final FileUploadService fileUploadService;

    @Override
    public String savePdf(MultipartFile multipartFile) {
        String originalFileName = multipartFile.getOriginalFilename();
        if (originalFileName == null || !isValidExtension(getExtension(originalFileName))) {
            throw ExtensionNotMatchException.EXCEPTION;
        }

        String folder = awsS3Properties.pdfFolder();
        String date = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        String key = folder + date + "-" + UUID.randomUUID() + ".pdf";

        fileUploadService.uploadFile(multipartFile, key);

        return key;
    }

    @Override
    public String getPdfFileUrl(String filePath) {
        return s3Operations.createSignedGetURL(awsS3Properties.bucket(), filePath, Duration.ofHours(4)).toString();
    }

    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".")).toLowerCase();
    }

    private boolean isValidExtension(String extension) {
        return extension.equals(".pdf");
    }
}
