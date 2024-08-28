package com.repo.whopper.interfaces.resume.dto.request;

import java.util.List;

public record UpdateListRequest<T>(
        List<T> list
) {}