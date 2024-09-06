package com.repo.whopper.interfaces.file;

import com.repo.whopper.application.file.usecase.ImageUseCase;
import com.repo.whopper.interfaces.file.response.ImagePathResponse;
import com.repo.whopper.domain.file.type.ImageType;
import com.repo.whopper.common.annotation.OnlyStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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
        return new ImagePathResponse(imageUseCase.getFileBaseUrl() + path, filePart.getOriginalFilename());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteImage(@RequestBody UrlRequest request) {
        imageUseCase.deleteImage(request.url);
    }

    record UrlRequest(String url) {}
}