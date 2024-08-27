package com.example.whopper.interfaces.resume.dto.request;

import java.util.Set;

public record UpdateWriterInfoRequest(
        String email,
        Set<String> skillSet,
        String url,
        String majorId
) {}