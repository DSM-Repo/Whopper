package com.example.whopper.lagacy.document.dto.request;

import java.util.List;

public record UpdateListRequest<T>(
        List<T> list
) {
}
