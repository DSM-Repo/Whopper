package com.example.whopper.interfaces.library.dto.response;

import com.example.whopper.interfaces.library.dto.LibraryElementDto;

import java.util.List;

public record LibraryIndexResponse(
        List<LibraryElementDto.ResumeIndex> index
) {
}
