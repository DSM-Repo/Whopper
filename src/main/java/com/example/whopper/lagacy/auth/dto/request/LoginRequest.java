package com.example.whopper.lagacy.auth.dto.request;

public record LoginRequest(
        String accountId,
        String password
) {
}
