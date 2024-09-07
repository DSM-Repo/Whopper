package com.repo.whopper.interfaces.resume.dto.request;

import java.util.List;

public record UpdateWriterInfoRequest(
        String email,
        List<String> skillSet,
        String url,
        String majorName
) {}