package com.example.whopper.domain.library.dto.response;

import com.example.whopper.domain.library.domain.DocumentIndex;

import java.util.List;

public record LibraryIndexResponse(
        List<DocumentIndex> index
) {
}
