package com.dsm.repo.external.web.rest.auth.dto.request;

public record LoginRequest(
        String accountId,
        String password
) {}