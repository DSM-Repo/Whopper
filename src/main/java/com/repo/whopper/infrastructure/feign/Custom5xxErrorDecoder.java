package com.repo.whopper.infrastructure.feign;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

public class Custom5xxErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        var exception = defaultErrorDecoder.decode(methodKey, response);

        if (response.status() >= 500) {
            return new RetryableException(
                    response.status(),
                    exception.getMessage(),
                    response.request().httpMethod(),
                    exception,
                    (Long) null,
                    response.request()
            );
        }

        return exception;
    }
}