package com.dsm.repo.external.web.rest.resume.dto.request;

import java.util.List;

public record UpdateWriterInfoRequest(
        String email,
        List<String> skillSet,
        String url,
        String major
) {}