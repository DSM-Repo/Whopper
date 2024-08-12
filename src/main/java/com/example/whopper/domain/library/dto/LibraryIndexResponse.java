package com.example.whopper.domain.library.dto;

import com.example.whopper.domain.library.domain.DocumentIndex;
import com.example.whopper.global.utils.DataResponseInfo;

import java.util.List;

public record LibraryIndexResponse(
        DataResponseInfo<DocumentIndex> index
) {
}
