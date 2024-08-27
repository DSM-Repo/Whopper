package com.example.whopper.interfaces.library.dto.request;

import com.example.whopper.interfaces.library.dto.LibraryElementDto;

import java.util.List;

public record ResumeIndexRequest(
        List<LibraryElementDto.ResumeIndex> index
) {
}
