package com.repo.whopper.interfaces.library.dto.response;

import com.repo.whopper.interfaces.library.dto.LibraryElementDto;

import java.util.List;

public record LibraryIndexResponse(
        List<LibraryElementDto.ResumeIndex> index
) {
}
