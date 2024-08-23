package com.example.whopper.interfaces.library.dto.request;

import com.example.whopper.domain.library.DocumentIndex;

import java.util.List;

public record DocumentIndexRequest(
        List<DocumentIndex> index
) {
}
