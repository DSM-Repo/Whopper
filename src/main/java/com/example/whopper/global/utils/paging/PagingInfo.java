package com.example.whopper.global.utils.paging;

import java.util.List;

public record PagingInfo<T>(
        List<T> data,
        int dataOfNumber
) {
}
