package com.example.whopper.application.file.impl;

import com.example.whopper.application.file.usecase.PdfUseCase;
import com.example.whopper.common.exception.file.ExtensionNotMatchException;
import com.example.whopper.common.exception.file.PdfUploadFailedException;
import com.example.whopper.infrastructure.aws.s3.AwsS3FileType;
import com.example.whopper.infrastructure.aws.s3.AwsS3Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.UploadRequest;

import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class PdfService implements PdfUseCase {

    private final AwsS3Properties awsS3Properties;
    private final S3TransferManager s3TransferManager;
    private final S3Presigner s3Presigner;

    private final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

    @Override
    public String savePdf(MultipartFile multipartFile) {
        String originalFileName = multipartFile.getOriginalFilename();
        if (originalFileName == null || !isValidExtension(getExtension(originalFileName))) {
            throw ExtensionNotMatchException.EXCEPTION;
        }

        String folder = awsS3Properties.pdfFolder();
        String date = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        String key = String.format("%s/%s-%s.pdf", folder, date, UUID.randomUUID());

        uploadFile(multipartFile, key);

        return key;
    }

    @Override
    public String getPdfFileUrl(String filePath) {
        GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofHours(4))
                .getObjectRequest(GetObjectRequest.builder()
                        .bucket(awsS3Properties.bucket())
                        .key(filePath)
                        .build())
                .build(); 

        return s3Presigner.presignGetObject(getObjectPresignRequest).url().toString();
    }

    private void uploadFile(MultipartFile multipartFile, String key) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(awsS3Properties.bucket())
                .key(key)
                .acl(AwsS3FileType.PDF.getCannedAcl())
                .build();

        // MultipartFile의 InputStream을 사용하여 파일을 S3에 업로드
        try (InputStream inputStream = multipartFile.getInputStream()) {
            // S3 업로드 수행
            var uploadFuture = s3TransferManager.upload(UploadRequest.builder()
                    .putObjectRequest(putObjectRequest)
                    .requestBody(AsyncRequestBody.fromInputStream(inputStream, multipartFile.getSize(), executorService)) // 수정된 부분
                    .build()).completionFuture();

            uploadFuture.whenComplete((result, exception) -> {
                if (exception != null) {
                    throw PdfUploadFailedException.EXCEPTION;
                }
            });
        } catch (Exception e) {
            throw PdfUploadFailedException.EXCEPTION;
        }
    }

    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".")).toLowerCase();
    }

    private boolean isValidExtension(String extension) {
        return extension.equals(".pdf");
    }
}
