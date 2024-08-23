package com.example.whopper.interfaces.library.dto.response;

import com.example.whopper.domain.library.DocumentIndex;

import java.util.List;

public record LibraryIndexResponse(
        List<DocumentIndex> index
) {
}
