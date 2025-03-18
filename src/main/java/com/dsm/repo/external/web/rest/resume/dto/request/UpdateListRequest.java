package com.dsm.repo.external.web.rest.resume.dto.request;

import java.util.List;

public record UpdateListRequest<T>(
        List<T> list
) {}