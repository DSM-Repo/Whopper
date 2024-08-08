package com.example.whopper.domain.auth.dto.request;

public record LoginRequest(
        String account_id,
        String password
) {
}
