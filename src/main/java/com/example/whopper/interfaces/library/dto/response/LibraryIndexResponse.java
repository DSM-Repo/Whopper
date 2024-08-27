package com.example.whopper.interfaces.library.dto.response;

import com.example.whopper.domain.library.ResumeIndex;

import java.util.List;

public record LibraryIndexResponse(
        List<ResumeIndex> index
) {
}
