package com.example.whopper.domain.library.application.component;

import com.example.whopper.domain.library.domain.DocumentIndex;
import com.example.whopper.global.utils.DataResponseInfo;
import org.springframework.web.multipart.MultipartFile;

public interface ParseDocumentIndexComponent {
    DataResponseInfo<DocumentIndex> parseDocumentIndex(MultipartFile indexPart);
}
