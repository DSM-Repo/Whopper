package com.example.whopper.domain.document.dto.request;

import java.util.List;

public record UpdateListRequest<T>(
        List<T> list
) {
}
