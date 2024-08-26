package com.example.whopper.application.library.impl;

import com.example.whopper.application.library.usecase.CreateLibraryUseCase;
import com.example.whopper.domain.library.LibraryMongoRepository;
import com.example.whopper.domain.library.ResumeIndex;
import com.example.whopper.domain.library.LibraryEntity;
import com.example.whopper.domain.library.type.AccessRight;
import com.example.whopper.common.http.response.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateLibraryService implements CreateLibraryUseCase {

    private final LibraryMongoRepository libraryMongoRepository;

    @Override
    @Transactional
    public void createLibrary(Integer grade, String filePath, DataResponseInfo<ResumeIndex> index) {
        LocalDateTime now = LocalDateTime.now();

        libraryMongoRepository.save(
                LibraryEntity.builder()
                        .year(now.getYear())
                        .grade(grade)
                        .filePath(filePath)
                        .createAt(now)
                        .accessRight(AccessRight.PRIVATE)
                        .index(index.data())
                        .build());
    }
}
