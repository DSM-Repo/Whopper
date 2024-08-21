package com.example.whopper.lagacy.library.dto.response;

import com.example.whopper.lagacy.library.domain.DocumentIndex;

import java.util.List;

public record LibraryIndexResponse(
        List<DocumentIndex> index
) {
}
