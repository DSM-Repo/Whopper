package com.example.whopper.interfaces.library.dto.request;

import java.util.List;

public record ResumeIndexRequest(
        List<ResumeIndex> index
) {
}
