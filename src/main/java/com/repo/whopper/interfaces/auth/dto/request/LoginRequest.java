package com.repo.whopper.interfaces.auth.dto.request;

public record LoginRequest(
        String accountId,
        String password
) {}