package com.example.whopper.domain.document.application.base;

public interface UpdateElementUseCase<T> {
    void update(T request);
}