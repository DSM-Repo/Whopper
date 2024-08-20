package com.example.whopper.domain.library.dto.request;

import com.example.whopper.domain.library.domain.DocumentIndex;

import java.util.List;

public record DocumentIndexRequest(
        List<DocumentIndex> index
) {
}
