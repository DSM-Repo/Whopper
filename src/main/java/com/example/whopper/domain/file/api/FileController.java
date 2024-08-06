package com.example.whopper.domain.file.api;

import com.example.whopper.domain.file.dto.response.ImagePathResponse;
import com.example.whopper.domain.file.type.ImageType;
import com.example.whopper.infra.s3.component.SaveImageComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final SaveImageComponent saveImageComponent;

    @PostMapping(value = "/image", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public Mono<ImagePathResponse> uploadImage(
            @RequestPart("file") MultipartFile filePart,
            @RequestParam("type") ImageType imageType) {

        // Save the image using the saveImageComponent
        return saveImageComponent.saveImage(filePart, imageType)
                .map(path -> new ImagePathResponse(path, saveImageComponent.getFileBaseUrl()));
    }
}