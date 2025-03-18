package com.dsm.repo.external.web.rest.common;

import java.util.List;

public record DataResponseInfo<T>(
        List<T> data,
        int numberOfData
) {

    public static <T> DataResponseInfo<T> of(List<T> data) {
        return new DataResponseInfo<>(data, data.size());
    }

}
