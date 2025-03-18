package com.dsm.repo.external.web.rest.library.dto.request;

import com.dsm.repo.external.web.rest.library.dto.LibraryElementDto;

import java.util.List;

public record ResumeIndexRequest(
        List<LibraryElementDto.ResumeIndex> index
) {
}
