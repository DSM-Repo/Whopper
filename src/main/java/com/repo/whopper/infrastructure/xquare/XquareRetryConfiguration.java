package com.repo.whopper.infrastructure.xquare;

import com.repo.whopper.infrastructure.feign.Custom5xxErrorDecoder;
import com.repo.whopper.infrastructure.feign.Custom5xxRetryer;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class XquareRetryConfiguration {

    @Bean
    public Retryer retryer() {
        return new Custom5xxRetryer();
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new Custom5xxErrorDecoder();
    }
}