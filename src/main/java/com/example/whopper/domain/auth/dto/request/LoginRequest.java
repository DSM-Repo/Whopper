package com.example.whopper.domain.auth.dto.request;

public record LoginRequest(
        String accountId,
        String password
) {
    public LoginRequest {
        accountId = accountId.toLowerCase();
    }
}
