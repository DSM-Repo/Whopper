package com.dsm.repo.external.web.rest.file;

import com.dsm.repo.internal.core.usecase.file.ImageUseCase;
import com.dsm.repo.external.web.rest.file.response.ImagePathResponse;
import com.dsm.repo.internal.data.property.file.type.ImageType;
import com.dsm.repo.external.security.auth.annotation.OnlyStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
class FileController {

    private final ImageUseCase imageUseCase;

    @OnlyStudent
    @PostMapping(value = "/image", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    ImagePathResponse uploadImage(
            @RequestPart("file") MultipartFile filePart,
            @RequestParam("type") ImageType imageType
    ) {
        String path = imageUseCase.saveImage(filePart, imageType);
        return new ImagePathResponse(imageUseCase.getFileBaseUrl() + path, filePart.getOriginalFilename());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteImage(@RequestParam String url) {
        imageUseCase.deleteImage(url);
    }

}