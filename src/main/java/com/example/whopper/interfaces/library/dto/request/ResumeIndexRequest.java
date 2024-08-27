package com.example.whopper.interfaces.library.dto.request;

import com.example.whopper.domain.library.ResumeIndex;

import java.util.List;

public record ResumeIndexRequest(
        List<ResumeIndex> index
) {
}
