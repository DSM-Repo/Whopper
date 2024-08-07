package com.example.whopper.domain.file.application.impl;

import com.example.whopper.domain.file.application.usecase.SavePdfUseCase;
import com.example.whopper.infra.s3.AwsS3FileType;
import com.example.whopper.infra.s3.AwsS3Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.UploadRequest;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class SavePdfService implements SavePdfUseCase {

    private final AwsS3Properties awsS3Properties;
    private final S3TransferManager s3TransferManager;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public String savePdf(MultipartFile multipartFile) {
        String folder = awsS3Properties.pdfFolder();
        String date = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        String key = String.format("%s/%s-%s.pdf", folder, date, UUID.randomUUID());

        uploadFile(multipartFile, key);

        return key;
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
            s3TransferManager.upload(UploadRequest.builder()
                    .putObjectRequest(putObjectRequest)
                    .requestBody(AsyncRequestBody.fromInputStream(inputStream, multipartFile.getSize(), executorService)) // 수정된 부분
                    .build()).completionFuture().join(); // 동기적으로 대기
        } catch (Exception e) {
            throw new RuntimeException("파일 업로드 중 오류 발생", e);
        }
    }
}