package com.example.whopper.domain.file.api;

import com.example.whopper.domain.file.application.usecase.SaveImageUseCase;
import com.example.whopper.domain.file.dto.response.ImagePathResponse;
import com.example.whopper.domain.file.type.ImageType;
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

    private final SaveImageUseCase saveImageUseCase;

    @PostMapping(value = "/image", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ImagePathResponse uploadImage(
            @RequestPart("file") MultipartFile filePart,
            @RequestParam("type") ImageType imageType) {

        // Save the image using the saveImageComponent
        String path = saveImageUseCase.saveImage(filePart, imageType);
        return new ImagePathResponse(path, saveImageUseCase.getFileBaseUrl());
    }
}