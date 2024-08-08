package com.example.whopper.domain.file.api;

import com.example.whopper.domain.file.application.usecase.ImageUseCase;
import com.example.whopper.domain.file.dto.response.ImagePathResponse;
import com.example.whopper.domain.file.type.ImageType;
import com.example.whopper.global.annotation.OnlyStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final ImageUseCase imageUseCase;

    @OnlyStudent
    @PostMapping(value = "/image", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ImagePathResponse uploadImage(@RequestPart("file") MultipartFile filePart, @RequestParam("type") ImageType imageType) {
        String path = imageUseCase.saveImage(filePart, imageType);
        return new ImagePathResponse(path, imageUseCase.getFileBaseUrl());
    }
}