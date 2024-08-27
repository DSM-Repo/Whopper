package com.example.whopper.interfaces.auth.dto.request;

public record LoginRequest(
        String accountId,
        String password
) {}