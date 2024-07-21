package com.example.whopper.domain.auth.dto.request;

public record StudentLoginRequest(
        String accountId,
        String password
) {
}
