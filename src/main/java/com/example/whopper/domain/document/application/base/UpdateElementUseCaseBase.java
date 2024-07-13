package com.example.whopper.domain.document.application.base;

public interface UpdateElementUseCaseBase<T> {
    void update(T request);
}