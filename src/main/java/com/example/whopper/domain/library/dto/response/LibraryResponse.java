package com.example.whopper.domain.library.dto.response;

import com.example.whopper.domain.library.domain.type.AccessRight;

public record LibraryResponse(
        String id,
        AccessRight access_right,
        Integer year,
        Integer grade,
        Integer generation,
        String document_url
) {
}
