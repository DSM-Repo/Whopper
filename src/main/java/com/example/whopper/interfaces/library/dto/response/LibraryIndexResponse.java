package com.example.whopper.interfaces.library.dto.response;

import com.example.whopper.domain.library.LibraryEntity;

import java.util.List;

public record LibraryIndexResponse(
        List<LibraryEntity.ResumeIndex> index
) {
}
