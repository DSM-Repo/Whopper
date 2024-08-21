package com.example.whopper.lagacy.library.dto.request;

import com.example.whopper.lagacy.library.domain.DocumentIndex;

import java.util.List;

public record DocumentIndexRequest(
        List<DocumentIndex> index
) {
}
