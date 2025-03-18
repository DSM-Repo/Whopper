package com.dsm.repo.external.web.rest.library.dto.response;

import com.dsm.repo.external.web.rest.library.dto.LibraryElementDto;

import java.util.List;

public record LibraryIndexResponse(
        List<LibraryElementDto.ResumeIndex> index
) {
}
